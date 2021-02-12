package org.casey.cloud.h.common.core;

import lombok.Data;
import lombok.ToString;
import org.casey.cloud.h.common.core.enums.HttpCodeEnum;

import java.io.Serializable;

/**
 * @EnumName Result
 * @Author Fu Kai
 * @Description 统一消息Result
 * @Date 2021/1/23 14:15
 */

@Data
@ToString
public final class Result implements Serializable {
    private static final long serialVersionUID = -5085043698320938797L;
    private final int code;
    private final Object data;
    private final String message;

    private Result(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * 请求成功
     * 返回data和message
     */
    public static Result success(Object data, String message) {
        return new Result(HttpCodeEnum.SUCCESS.getCode(), data, message);
    }

    /**
     * 请求成功
     * 返回data和message
     */
    public static Result success(HttpCodeEnum httpCode, Object data, String message) {
        return new Result(httpCode.getCode(), data, message);
    }

    /**
     * 请求失败, 需指定HttpCode
     * 返回data和message
     */
    public static Result failure(HttpCodeEnum httpCode, Object data, String message) {
        return new Result(httpCode.getCode(), data, message);
    }
}