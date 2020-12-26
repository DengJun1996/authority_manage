package com.dj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.system.mapper.SysUserRoleMapper;
import com.dj.system.model.SysMenuEntity;
import com.dj.system.model.SysUserRoleEntity;
import com.dj.system.service.SysUserRoleService;

import com.dj.system.utils.Tree;
import com.dj.system.utils.TreeUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色中间表
 * @author jetlag
 * @date 2020/08/14
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleEntity> implements SysUserRoleService {

    private SysUserRoleMapper sysUserRoleMapper;

    public SysUserRoleServiceImpl(SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    @Override
    public List<Tree> indexMenu(String loginName) {
        List<SysMenuEntity> listSysMenu = sysUserRoleMapper.selectMenuByRoleId(loginName);
        return TreeUtil.findTree(listSysMenu);
    }

    @Override
    public void addSysUserRole(Long userId, Long roleId) {
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity(userId, roleId);
        sysUserRoleMapper.insert(sysUserRoleEntity);
    }

    @Override
    public boolean findBySysUserRole(Long userId, Long roleId) {
        Integer count = sysUserRoleMapper.selectCount(new LambdaQueryWrapper<SysUserRoleEntity>().eq(SysUserRoleEntity::getUserId, userId)
                .eq(SysUserRoleEntity::getRoleId, roleId));
        if (count > 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
