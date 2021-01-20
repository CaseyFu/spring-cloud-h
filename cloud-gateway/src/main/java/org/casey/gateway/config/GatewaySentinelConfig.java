package org.casey.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import org.casey.gateway.common.HttpCodeEnum;
import org.casey.gateway.common.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.PostConstruct;

/**
 * @ClassName GatewaySentinelConfig
 * @Author Fu Kai
 * @Description 自定义异常信息
 * @Date 2021/1/4 0:18
 */

@Configuration
public class GatewaySentinelConfig {

    @PostConstruct
    public void initBlockHandler() {
        BlockRequestHandler blockRequestHandler = (serverWebExchange, throwable) -> ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(Result.failed(HttpCodeEnum.UNAUTHORIZED, null, "block限流!")));
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }
}
