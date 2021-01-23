package org.casey.oauth2.auth.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @ClassName ClientAuthenticationFilter
 * @Author Fu Kai
 * @Description 自定义客户端认证过滤器
 * @Date 2021/1/16 11:43
 */


public class ClientAuthenticationFilter extends ClientCredentialsTokenEndpointFilter {

    private final AuthorizationServerSecurityConfigurer configurer;
    private AuthenticationEntryPoint authenticationEntryPoint;

    public ClientAuthenticationFilter(AuthorizationServerSecurityConfigurer configurer) {
        this.configurer = configurer;
    }

    @Override
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        super.setAuthenticationEntryPoint(null);
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected AuthenticationManager getAuthenticationManager() {
        return configurer.and().getSharedObject(AuthenticationManager.class);
    }

    @Override
    public void afterPropertiesSet() {
        setAuthenticationFailureHandler((request, response, e) -> authenticationEntryPoint.commence(request, response, e));
        setAuthenticationSuccessHandler((request, response, authentication) -> {
        });
    }
}