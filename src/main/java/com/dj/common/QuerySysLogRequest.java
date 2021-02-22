package com.dj.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jetlag
 * @date 2020/7/2
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class QuerySysLogRequest extends PageRequest {

    /**
     * 标题
     */
    private String title;
}
