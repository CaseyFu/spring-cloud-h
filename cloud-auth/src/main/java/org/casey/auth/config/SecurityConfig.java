package org.casey.auth.config;


import org.casey.common.core.Result;
import org.casey.common.core.enums.HttpCodeEnum;
import org.casey.common.json.JsonUtil;
import org.casey.common.web.util.HttpUtil;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @ClassName WebSecurityConfig
 * @Author Fu Kai
 * @Description 限制可访问路径
 * @Date 2021/1/5 17:09
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers("/rsa/publicKey", "/v2/**")
                .permitAll()
                .anyRequest().authenticated();

        // 认证失败和权限不足
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint());
    }

    /**
     * 自定义返回信息
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, e) -> {
            HttpUtil.packResponse(response);
            response.getWriter().write(JsonUtil.serialize(Result.failure(HttpCodeEnum.UNAUTHORIZED, null, "无相关权限, 服务端拒绝访问accessDeniedHandler!")));
            response.getWriter().flush();
        };
    }

    /**
     * 自定义返回信息
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            HttpUtil.packResponse(response);
            response.getWriter().print(JsonUtil.serialize(Result.failure(HttpCodeEnum.UNAUTHORIZED, null, "无权限访问authenticationEntryPoint!")));
            response.getWriter().flush();
        };
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}