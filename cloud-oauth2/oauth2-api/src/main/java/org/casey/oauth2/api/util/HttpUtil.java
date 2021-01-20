package org.casey.oauth2.api.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public static HttpServletResponse packageResponse(HttpServletResponse response) {
        response.setStatus(200);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return response;
    }

    public static HttpServletRequest getHttpServletRequest() {
        // 获取HttpServletRequest内置对象
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.currentRequestAttributes())).getRequest();
    }
}
