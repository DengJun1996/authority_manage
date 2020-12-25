package com.dj.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态枚举
 * @author jetlag
 * @date 2020/5/9
 **/
@Getter
@AllArgsConstructor
public enum SysUserStatusEnum {

    DEL_FLAG0(0, "正常"),
    DEL_FLAG1(1, "删除"),
    USER_UNFREEZE(0, "正常"),
    USER_FREEZE(1, "停用");

    private int code;

    private String msg;


}
