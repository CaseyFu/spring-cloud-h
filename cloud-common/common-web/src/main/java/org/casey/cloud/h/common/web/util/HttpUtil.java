package org.casey.cloud.h.common.web.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @ClassName HttpUtil
 * @Author Code2021
 * @Description HttpUtil
 * @Date 2020/12/13 12:40
 */

public class HttpUtil {
    /**
     * 封装response
     */
    public static void packResponse(HttpServletResponse response) {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"*");
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    }

    /**
     * 获取HttpServletRequest内置对象
     */
    public static HttpServletRequest httpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.currentRequestAttributes())).getRequest();
    }
}
