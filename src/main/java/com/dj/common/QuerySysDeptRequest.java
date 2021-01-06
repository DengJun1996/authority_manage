package com.dj.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jetlag
 * @date 2020/7/2
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class QuerySysDeptRequest extends PageRequest {

    /**
     * 部门名称
     */
    private String deptName;
}
