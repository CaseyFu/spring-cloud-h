package org.casey.cloud.h.oauth2.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.casey.cloud.h.common.core.Result;
import org.casey.cloud.h.common.core.enums.HttpCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName Oauth2ApiExceptionHandler
 * @Author Fu Kai
 * @Description 全局处理Oauth2抛出的异常
 * @Date 2021/1/5 17:19
 */

@Slf4j
@RestControllerAdvice
public class Oauth2ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        return Result.failure(HttpCodeEnum.CREATED, null, e.getMessage());
    }
}