package org.casey.cloud.h.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;

import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName SwaggerFilterHandler
 * @Author Fu Kai
 * @Description todo
 * @Date 2020/12/30 14:01
 */


@Slf4j
@Component
public class SwaggerFilter extends AbstractGatewayFilterFactory<SwaggerFilter.Config> {

    private static final String URI = "/v2/api-docs";

    public SwaggerFilter() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!config.isEnabled()) {
                return chain.filter(exchange);
            }
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();

            if (!StringUtils.endsWithIgnoreCase(path, URI)) {
                return chain.filter(exchange);
            }

            String uri = path.substring(path.indexOf("/", 2));
            ServerHttpRequest newRequest;
            try {
                newRequest = request.mutate().uri(new URI(uri)).build();
            } catch (URISyntaxException e) {
                log.error("URISyntaxException", e);
                return chain.filter(exchange);
            }
            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(newExchange);
        };
    }

    public static class Config {
        // 控制是否开启认证
        private boolean enabled;

        public Config() {
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

}