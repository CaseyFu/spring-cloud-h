package org.casey.oauth2.gateway.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import org.casey.common.core.consts.AuthConst;
import org.casey.common.core.consts.RedisConst;
import org.casey.oauth2.gateway.common.HttpCodeEnum;
import org.casey.oauth2.gateway.common.Result;
import org.casey.oauth2.gateway.filter.AllowedUriListRemoveJwtFilter;
import org.casey.oauth2.gateway.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ResourceServerConfig
 * @Author Fu Kai
 * @Description 资源服务器
 * @Date 2021/1/5 17:06
 */

@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {
    private final SecurityConfig securityConfig;
    private final AllowedUriListRemoveJwtFilter allowedUriListRemoveJwtFilter;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public ResourceServerConfig(SecurityConfig securityConfig, AllowedUriListRemoveJwtFilter allowedUriListRemoveJwtFilter, RedisTemplate<String, Object> redisTemplate) {
        this.securityConfig = securityConfig;
        this.allowedUriListRemoveJwtFilter = allowedUriListRemoveJwtFilter;
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        // 对白名单路径，直接移除JWT请求头
        http.addFilterBefore(allowedUriListRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());

        // 自定义返回JWT请求头过期或签名错误的结果
        http.oauth2ResourceServer().authenticationEntryPoint(serverAuthenticationEntryPoint());

        http.authorizeExchange()
                // 允许所有 OPTIONS
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                // 白名单配置
                .pathMatchers(ArrayUtil.toArray(securityConfig.getGatewayAllowedUriList(), String.class)).permitAll()
                // 鉴权管理器配置
                .anyExchange().access(authorizationManager());

        // 异常处理
        http.exceptionHandling()
                // 处理未认证
                .authenticationEntryPoint(serverAuthenticationEntryPoint())
                // 处理未授权
                .accessDeniedHandler(serverAccessDeniedHandler());
        http.csrf().disable();
        return http.build();
    }


    /**
     * @link https://blog.csdn.net/qq_24230139/article/details/105091273
     * ServerHttpSecurity没有将jwt中authorities的负载部分当做Authentication
     * 需要把jwt的Claim中的authorities加入
     * 方案：重新定义ReactiveAuthenticationManager权限管理器，默认转换器JwtGrantedAuthoritiesConverter
     */
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConst.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConst.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }


    /**
     * 重写 AuthorizationManager 的check方法
     * 鉴权管理器, 用于判断是否有资源的访问权限
     */
    @Bean
    public ReactiveAuthorizationManager<AuthorizationContext> authorizationManager() {
        return (Mono<Authentication> mono, AuthorizationContext authorizationContext) -> {
            // 从Redis中获取当前路径可访问角色列表
            URI uri = authorizationContext.getExchange().getRequest().getURI();
            Object obj = redisTemplate.opsForHash().get(RedisConst.RESOURCE_ROLES_MAP, uri.getPath());
            List<String> authorities = Convert.toList(String.class, obj);
            authorities = authorities.stream().map(i -> i = AuthConst.AUTHORITY_PREFIX + i).collect(Collectors.toList());
            // 认证通过且角色匹配的用户可访问当前路径
            return mono.filter(Authentication::isAuthenticated)
                    .flatMapIterable(Authentication::getAuthorities)
                    .map(GrantedAuthority::getAuthority)
                    .any(authorities::contains)
                    .map(AuthorizationDecision::new)
                    .defaultIfEmpty(new AuthorizationDecision(false));
        };
    }

    /**
     * 自定义返回结果, 没有权限访问时
     */
    @Bean
    public ServerAccessDeniedHandler serverAccessDeniedHandler() {
        return (ServerWebExchange exchange, AccessDeniedException exception) -> {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            String body = JsonUtil.serialize(Result.failed(HttpCodeEnum.UNAUTHORIZED, null, exception.getMessage()));
            DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        };
    }

    /**
     * 自定义返回结果, 没有登录或token过期时
     */
    @Bean
    public ServerAuthenticationEntryPoint serverAuthenticationEntryPoint() {
        return (ServerWebExchange exchange, AuthenticationException exception) -> {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            String body = JsonUtil.serialize(Result.failed(HttpCodeEnum.UNAUTHORIZED, null, exception.getMessage()));
            DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        };
    }

}