package org.casey.cloud.h.oauth2.gateway.filter;

import org.casey.cloud.h.oauth2.gateway.config.SecurityConfig;
import org.casey.cloud.h.common.core.consts.AuthConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * @ClassName IgnoreUrlsRemoveJwtFilter
 * @Author Fu Kai
 * @Description todo
 * @Date 2020/12/25 12:14
 */

@Component

public class AllowedUriListRemoveJwtFilter implements WebFilter {

    private final SecurityConfig securityConfig;
    private final PathMatcher pathMatcher;

    @Autowired
    public AllowedUriListRemoveJwtFilter(SecurityConfig securityConfig, PathMatcher pathMatcher) {
        this.securityConfig = securityConfig;
        this.pathMatcher = pathMatcher;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();

        // 白名单路径移除请求头
        List<String> allowedUriList = securityConfig.getGatewayAllowedUriList();
        for (String allowedUri : allowedUriList) {
            if (pathMatcher.match(allowedUri, uri.getPath())) {
                request = exchange.getRequest().mutate()
                        .header(AuthConst.AUTHORIZATION_HEADER, "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }
}