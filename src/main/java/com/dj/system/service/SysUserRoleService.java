package com.dj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.system.model.SysUserRoleEntity;
import com.dj.system.utils.Tree;

import java.util.List;

/**
 * @author dj
 * @date 2020-06-22
 * @company www.dfdk.com.cn
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
