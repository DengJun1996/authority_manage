package com.dj.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误类型码 持续补充中
 * @author jetlag
 * @date 2020/6/1
 **/
@Getter
@AllArgsConstructor
public enum ResultEnum {

    /**
     * 请求成功
     */
    SUCCESS(0, "请求成功"),

    /**
     * 系统错误
     */
    UNKONW_ERROR(500, "系统错误"),

    // ------------系统返回码  1000~1999段 -----------------
    UNKNOWN_ACCOUNT(1000, "该账号不存在"),

    INCORRECT_CREDENTIALS(1001, "账号或密码不正确"),

    LOCKED_ACCOUNT(1002, "账号已被锁定,请联系管理员"),

    AUTHENTICATION(1003, "账户验证失败"),

    UN_AUTHORIZED(1004, "权限不足");

    /** 返回码*/
    private Integer code;

    /** 返回描述*/
    private String msg;

}
