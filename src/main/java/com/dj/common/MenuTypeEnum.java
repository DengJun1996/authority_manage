package com.dj.common;

import com.dj.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 菜单目录类型枚举
 * @author jetlag
 * @date 2020/6/23
 **/
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    M,

    C,

    F;

    public static MenuTypeEnum getMenuTypeEnum(String name) {
        return Arrays.stream(MenuTypeEnum.values())
                .filter(val -> val.name().equalsIgnoreCase(name))
                .findAny().orElseThrow(() -> new BaseException("菜单类型不合法"){
                    private static final long serialVersionUID = 2900333135446478198L;
                });
    }
}
