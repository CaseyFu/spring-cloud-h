package org.casey.oauth2.auth.exception;

import org.casey.common.core.Result;
import org.casey.common.core.enums.HttpCodeEnum;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName Oauth2ExceptionHandler
 * @Author Fu Kai
 * @Description 全局处理Oauth2抛出的异常
 * @Date 2021/1/5 17:19
 */

@ControllerAdvice
public class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)

    public Result handleOauth2(OAuth2Exception e) {
        return Result.failed(HttpCodeEnum.FORBIDDEN, null, e.getMessage());
    }
}