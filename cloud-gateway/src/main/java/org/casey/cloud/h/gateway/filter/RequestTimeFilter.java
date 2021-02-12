package org.casey.cloud.h.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName RequestTimeFilter
 * @Author Fu Kai
 * @Description 耗时过滤器, order越大优先级越低, 必须注册Filter为bean, GatewayFilter需要加在predicates中
 * @Date 2021/1/4 15:15
 */
@Slf4j
@Component
public class RequestTimeFilter extends AbstractGatewayFilterFactory<RequestTimeFilter.Config> {

    public RequestTimeFilter() {
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
            exchange.getAttributes().put("beginTime", System.currentTimeMillis());
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute("beginTime");
                        if (startTime != null) {
                            String msg = exchange.getRequest().getURI().getRawPath() +
                                    "耗时: " +
                                    (System.currentTimeMillis() - startTime) +
                                    "ms";
                            log.info(msg);
                        }
                    })
            );
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
