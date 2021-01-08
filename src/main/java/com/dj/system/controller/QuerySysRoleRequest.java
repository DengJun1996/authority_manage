package com.dj.system.controller;

import com.dj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jetlag
 * @date 2020/7/2
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class QuerySysRoleRequest extends PageRequest {

    private String roleName;
}
