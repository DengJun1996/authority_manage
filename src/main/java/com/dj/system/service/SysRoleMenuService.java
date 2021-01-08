package com.dj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.system.model.SysRoleMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * @author wxl
 * @date 2020-03-19 上午10:53
 * @company www.dfdk.com.cn
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

    /**
     * 根据角色ID获取菜单ID集合
     * @author wxl
     * @date 2020-03-19
     * @param roleId 角色ID
     * @return
     **/
    List<Long> listMenuIds(Long roleId);

    /**
     * 根据菜单ID删除
     * @author wxl
     * @date 2020-03-19
     * @param menuIds 菜单IDS
     * @return
     **/
    boolean removeByMenuId(List<Long> menuIds);

    /**
     * 根据角色ID删除
     * @param roleIds 角色id集合
     * @return
     */
    boolean removeByRoleId(List<Long> roleIds);

    /**
     * 查询所有菜单的集合，树形结构
     * @return
     */
    List<Object> listAllMenu();

    /**
     * 根据角色id获取拥有的菜单
     */
    Map<String,Object> listMenuByRoleID(Long roleId);
}
