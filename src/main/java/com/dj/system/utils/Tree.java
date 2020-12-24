package com.dj.system.utils;


import lombok.Data;

import java.util.List;

/**
 * @author jetlag
 * @date 2020/6/22
 **/
@Data
public class Tree {

    private String menuId;

    private String href;

    private String icon;


    /**
     * 父ID
     */
    private String pid;

    /**
     * 标题
     */
    private String title;

    /**
     * 排序
     */
    private String orderNum;

    /**
     * 子树集
     */
    private List<Tree> childTree;

}
