package org.casey.oauth2.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.casey.common.core.Result;
import org.casey.common.core.enums.HttpCodeEnum;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName Oauth2ExceptionHandler
 * @Author Fu Kai
 * @Description 全局处理Oauth2抛出的异常
 * @Date 2021/1/5 17:19
 */

@Slf4j
@RestControllerAdvice
public class Oauth2AuthExceptionHandler {
    /**
     * 用户名或密码错误自定义异常返回
     */
    @ExceptionHandler(InvalidGrantException.class)
    public Result invalidGrantException(InvalidGrantException e) {
        return Result.failure(HttpCodeEnum.FORBIDDEN, null, e.getMessage());
    }

    /**
     * 账户异常(禁用、锁定、过期)
     */
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public Result internalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        return Result.failure(HttpCodeEnum.FORBIDDEN, null, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        return Result.failure(HttpCodeEnum.INTERNAL_SERVER_ERROR, null, e.getMessage());
    }


}