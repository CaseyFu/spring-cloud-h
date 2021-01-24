package org.casey.oauth2.auth.config;

import org.casey.common.core.Result;
import org.casey.common.core.enums.HttpCodeEnum;
import org.casey.common.json.JsonUtil;
import org.casey.common.web.util.HttpUtil;
import org.casey.oauth2.auth.domain.SecurityUser;
import org.casey.oauth2.auth.filter.ClientCredentialsTokenEndpointFilterSuccessor;
import org.casey.oauth2.auth.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AuthorizationServerConfig
 * @Author Fu Kai
 * @Description 授权服务器
 * @Date 2021/1/5 17:06
 */

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public AuthorizationServerConfig(PasswordEncoder passwordEncoder, UserServiceImpl userDetailsService, AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    /**
     * 配置AuthorizationServer安全认证的相关信息, 创建ClientCredentialsTokenEndpointFilter核心过滤器
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {

        ClientCredentialsTokenEndpointFilter endpointFilter = new ClientCredentialsTokenEndpointFilterSuccessor(security);
        endpointFilter.afterPropertiesSet();
        endpointFilter.setAuthenticationEntryPoint(authenticationEntryPoint);
        security.addTokenEndpointAuthenticationFilter(endpointFilter);

        security.authenticationEntryPoint(authenticationEntryPoint)
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }


    private static final String CLIENT_ID = "cloud-oauth2-app";
    private static final String SECRET = "2168230078";
    private static final String SCOPES = "all";
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1800;
    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 3600;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(SECRET))
                .scopes(SCOPES)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS)
                .authorizedGrantTypes("password", "refresh_token");
    }

    /**
     * 配置身份认证器, 配置认证方式, TokenStore, TokenGranter, OAuth2RequestFactory
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(tokenEnhancer());
        delegates.add(accessTokenConverter());
        // 配置JWT的内容增强器
        enhancerChain.setTokenEnhancers(delegates);

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain)
                .reuseRefreshTokens(false);
    }



    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            Map<String, Object> map = new HashMap<>(4);
            SecurityUser user = (SecurityUser) authentication.getUserAuthentication().getPrincipal();
            map.put("id", user.getId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
            return accessToken;
        };
    }

    /**
     * 从classpath下的证书中获取秘钥对 注册为Bean
     */
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "2168230078".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "2168230078".toCharArray());
    }

}