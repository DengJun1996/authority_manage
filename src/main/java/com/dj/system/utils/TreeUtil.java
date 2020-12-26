package com.dj.system.utils;


import com.dj.system.model.SysMenuEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * @author jetlag
 * @date 2020/6/22
 **/
public class TreeUtil<T> {

    private final static String DEFAULT_ROOT_PID = "0";

    /**
     * 排序,根据order排序
     *
     * @return
     */
    public static Comparator<Tree> order() {
        return new Comparator<Tree>() {
            @Override
            public int compare(Tree o1, Tree o2) {
                if (!o1.getOrderNum().equals(o2.getOrderNum())) {
                    return Integer.parseInt(o1.getOrderNum()) - Integer.parseInt(o2.getOrderNum());
                }
                return 0;
            }
        };
    }


    /**
     * @param sysMenuList
     * @return
     */
    public static List<Tree> findTree(List<SysMenuEntity> sysMenuList) {
        List<Tree> allMenu = new ArrayList<>();
        sysMenuList.forEach(val -> {
            Tree menu = new Tree();
            menu.setMenuId(String.valueOf(val.getMenuId()));
            menu.setPid(val.getPid());
            menu.setHref(val.getUrl());
            menu.setIcon(val.getIcon());
            menu.setTitle(val.getMenuName());
            menu.setOrderNum(val.getOrderNum());
            allMenu.add(menu);
        });

        //根节点
        List<Tree> rootMenu = new ArrayList<>();
        for (Tree nav : allMenu) {
            if (nav.getPid().equals(DEFAULT_ROOT_PID)) {
                rootMenu.add(nav);
            }
        }

        /* 根据Menu类的order排序 */
        rootMenu.sort(order());
        //为根菜单设置子菜单，getClild是递归调用的
        for (Tree nav : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<Tree> childList = getChild(nav.getMenuId(), allMenu);
            //给根节点设置子节点
            nav.setChildTree(childList);
        }
        return rootMenu;
    }

    /**
     * 获取子节点
     *
     * @param id      父节点id
     * @param allMenu 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public static List<Tree> getChild(String id, List<Tree> allMenu) {
        //子菜单
        List<Tree> childList = new ArrayList<>();
        for (Tree nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if (nav.getPid().equals(id)) {
                childList.add(nav);
            }
        }
        //递归
        for (Tree nav : childList) {
            nav.setChildTree(getChild(nav.getMenuId(), allMenu));
        }
        //排序
        childList.sort(order());
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<>();
        }
        return childList;
    }
}
