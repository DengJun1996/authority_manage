package com.dj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.common.MenuTypeEnum;
import com.dj.common.QuerySysMenuRequest;
import com.dj.common.ResEntity;
import com.dj.system.model.SysMenuEntity;

/**
 * @author wxl
 * @date 2020-03-17 上午9:29
 * @company www.dfdk.com.cn
 */
public interface SysMenuService extends IService<SysMenuEntity> {
    ResEntity<?> removeByMenuId(Long menuId);

    /**
     * 根据菜单名称查询数据
     * @param menuName 菜单名称
     * @return
     */
    SysMenuEntity getByMenuName(String menuName);

    /**
     * 获取菜单树
     * @param menuTypeEnum
     * @return
     */
    ResEntity<?> getSupMenuTree(MenuTypeEnum menuTypeEnum);

    /**
     * 分页查询菜单数据
     * @param request
     * @return
     */
    ResEntity<?> querySysMenuPage(QuerySysMenuRequest request);

    ResEntity<?> saveOrUpdateMenu(SysMenuEntity sysMenuEntity);
}
