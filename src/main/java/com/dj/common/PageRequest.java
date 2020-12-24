package com.dj.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jetlag
 * @date 2020/7/1
 **/
@Getter
@Setter
public class PageRequest {

    /**
     * 当前页 默认 第一页
     */
    private long currentPage = 1;

    /**
     * 总页数
     */
    private long totalCount;

    /**
     * 页数大小 默认每页 10条
     */
    private long pageSize = 10;


    private long startPage;

    public void setStartPage(long startPage) {
        this.startPage = (this.currentPage - 1) * this.getPageSize();
    }
}
