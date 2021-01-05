package com.dj.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jetlag
 * @date 2020/7/2
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class QuerySysMenuRequest extends PageRequest {

    /**
     * 菜单名称
     */
    private String menuName;
}
