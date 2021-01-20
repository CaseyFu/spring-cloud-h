package org.casey.oauth2.gateway.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.casey.oauth2.gateway.util.StringUtil;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Author Fu Kai
 * @Description 统一消息返回对象
 * @Date 2020/11/19
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result implements Serializable {

    /**
     * HTTP状态码
     */
    private int code;

    /**
     * 数据
     */
    private Object data;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 请求成功
     * 返回data和message
     */
    public static Result success(Object data, String message) {
        return new Result(HttpCodeEnum.SUCCESS.getCode(), data, message);
    }

    /**
     * 请求失败, 需指定HttpCode
     * 返回data和message
     */
    public static Result failed(HttpCodeEnum httpCodeEnum, Object data, String message) {
        Result result = new Result();
        result.setCode(httpCodeEnum.getCode());
        result.setData(data);
        if (StringUtil.isBlank(message)) {
            result.setMessage(httpCodeEnum.getMessage());
        } else {
            result.setMessage(message);
        }
        return result;
    }
}
