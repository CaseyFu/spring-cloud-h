package org.casey.cloud.h.oauth2.gateway.config;

import io.netty.channel.ConnectTimeoutException;
import org.casey.cloud.h.common.core.Result;
import org.casey.cloud.h.common.core.enums.HttpCodeEnum;
import org.casey.cloud.h.common.json.JsonUtil;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.SignatureException;
import java.util.Map;

/**
 * @ClassName DefaultErrorWebExceptionHandlerSuccessor
 * @Author Fu Kai
 * @Description 重写 renderErrorResponse 方法
 * @Date 2021/1/24 16:53
 */

public class DefaultErrorWebExceptionHandlerSuccessor extends DefaultErrorWebExceptionHandler {

    public DefaultErrorWebExceptionHandlerSuccessor(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> error = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        Throwable throwable = getError(request);
        Result result;
        if (throwable instanceof SignatureException) {
            result = Result.failure(HttpCodeEnum.UNAUTHORIZED, null, "无权限!");
        } else if (throwable instanceof NotFoundException) {
            result = Result.failure(HttpCodeEnum.NOT_FOUND, null, "服务未找到!");
        } else if (throwable instanceof ResponseStatusException) {
            result = Result.failure(HttpCodeEnum.UNAUTHORIZED, null, "无权限!");
        } else if (throwable instanceof ConnectTimeoutException) {
            result = Result.failure(HttpCodeEnum.INTERNAL_SERVER_ERROR, null, "网关超时!");
        } else {
            result = Result.failure(HttpCodeEnum.INTERNAL_SERVER_ERROR, null, "服务器内部错误!");
        }
        String body = JsonUtil.serialize(result);
        return ServerResponse
                .status(super.getHttpStatus(error))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body.getBytes(StandardCharsets.UTF_8)));
    }

}