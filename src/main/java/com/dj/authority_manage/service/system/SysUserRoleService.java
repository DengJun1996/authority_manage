package com.dj.authority_manage.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.authority_manage.entity.system.SysUserRoleEntity;
import com.dj.authority_manage.utils.Tree;

import java.util.List;

/**
 * @author dj
 * @date 2020-06-22
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    /**
     * 首页菜单
     * @param loginName
     * @return
     */
    List<Tree> indexMenu(String loginName);

    /**
     * 新增用户角色中间表
     * @param userId 用户ID
     * @param roleId 角色ID
     */
    void addSysUserRole(Long userId, Long roleId);

    /**
     * 查询用户角色中间表
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return
     */
    boolean findBySysUserRole(Long userId, Long roleId);
}
