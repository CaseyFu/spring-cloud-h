package org.casey.oauth2.gateway.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;

/**
 * @ClassName BeanConfig
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/5 23:18
 */

@Configuration
@Data
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix = "spring.security")
public class SecurityConfig {
    private List<String> gatewayAllowedUriList;

    @Bean
    public PathMatcher pathMatcher() {
        return new AntPathMatcher();
    }
}
