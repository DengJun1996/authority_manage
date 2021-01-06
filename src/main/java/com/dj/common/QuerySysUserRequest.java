package com.dj.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jetlag
 * @date 2020/7/2
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class QuerySysUserRequest extends PageRequest {

    private String userName;

    private Long deptId;

    private String phoneNumber;

}
