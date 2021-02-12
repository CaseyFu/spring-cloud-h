package org.casey.cloud.h.common.core.enums;

/**
 * @EnumName StatusCode
 * @Author Fu Kai
 * @Description Http状态码
 * @Date 2020/7/1
 */

public enum HttpCodeEnum {
    /**
     * 请求成功, 一般用于GET与POST请求
     */
    SUCCESS(200, "Success"),
    /**
     * 已创建, 成功请求并创建了新的资源
     */
    CREATED(201, "Created"),
    /**
     * 已接受, 已经接受请求, 但未处理完成
     */
    ACCEPTED(202, "Accepted"),
    /**
     * 无效请求, 用户发出的请求有错误，服务器没有进行新建或修改数据的操作
     */
    INVALID_REQUEST(400, "Invalid Request"),
    /**
     * 未授权
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * 服务器理解请求客户端的请求, 但是拒绝执行此请求
     */
    FORBIDDEN(403, "Forbidden"),
    /**
     * 未找到
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * 请求失败, 服务端内部错误
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    /**
     * 不合法的token
     */
    ILLEGAL_TOKEN(50008, "Illegal Token"),
    /**
     * 已在其它客户端登录
     */
    OTHER_CLIENTS_LOGGED_IN(50012, "Other Clients Logged In"),
    /**
     * token过期
     */
    TOKEN_EXPIRED(50014, "Token Expired");


    private final Integer code;
    private final String message;

    HttpCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
