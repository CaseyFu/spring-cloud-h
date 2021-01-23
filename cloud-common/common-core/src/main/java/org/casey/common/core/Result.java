package org.casey.common.core;

import lombok.Data;
import lombok.ToString;
import org.casey.common.core.enums.HttpCodeEnum;

import java.io.Serializable;

/**
 * @EnumName Result
 * @Author Fu Kai
 * @Description 单例 枚举 统一消息返回对象
 * @Date 2021/1/23 14:15
 */

@Data
@ToString
public class Result implements Serializable {

    private static final long serialVersionUID = -5085043698320938797L;

    enum ResultSingletonEnum {
        /**
         * 单实例
         */
        INSTANCE;
        private final Result instance;

        ResultSingletonEnum() {
            instance = new Result();
        }

        public Result getInstance() {
            return instance;
        }
    }

    public static Result getInstance() {
        return ResultSingletonEnum.INSTANCE.getInstance();
    }

    /**
     * http状态码
     */
    private int code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 消息
     */
    private String msg;

    private Result() {
    }

    /**
     * 请求成功
     * 返回data和message
     */
    public static Result success(Object data, String message) {
        Result result = getInstance();
        result.setCode(HttpCodeEnum.SUCCESS.getCode());
        result.setData(data);
        result.setMsg(message);
        return result;
    }

    /**
     * 请求失败, 需指定HttpCode
     * 返回data和message
     */
    public static Result failure(HttpCodeEnum httpCode, Object data, String message) {
        Result result = getInstance();
        result.setCode(httpCode.getCode());
        result.setData(data);
        result.setMsg(message);
        return result;
    }
}