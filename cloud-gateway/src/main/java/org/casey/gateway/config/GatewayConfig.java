package org.casey.gateway.config;

import org.casey.common.core.Result;
import org.casey.common.core.enums.HttpCodeEnum;
import org.casey.common.json.JsonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName GatewayConfig
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/23 19:13
 */

@Configuration
public class GatewayConfig {

    /**
     * 自定义返回结果, 没有权限访问时
     */
    @Bean
    public ServerAccessDeniedHandler serverAccessDeniedHandler() {
        return (ServerWebExchange exchange, AccessDeniedException exception) -> {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            String body = JsonUtil.serialize(Result.failure(HttpCodeEnum.UNAUTHORIZED, null, "ServerAccessDeniedHandler"));
            DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        };
    }

    /**
     * 自定义返回结果, 没有登录或token过期时, 或模块抛异常
     */
    @Bean
    public ServerAuthenticationEntryPoint serverAuthenticationEntryPoint() {
        return (ServerWebExchange exchange, AuthenticationException exception) -> {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            String body = JsonUtil.serialize(Result.failure(HttpCodeEnum.UNAUTHORIZED, null, "ServerAuthenticationEntryPoint"));
            DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        };
    }
}
