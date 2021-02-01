package com.dj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.common.ResEntity;
import com.dj.common.SysRoleRequest;
import com.dj.system.controller.BaseController;
import com.dj.common.QuerySysRoleRequest;
import com.dj.system.mapper.SysRoleMapper;
import com.dj.system.mapper.SysUserRoleMapper;
import com.dj.system.model.SysRoleEntity;
import com.dj.system.model.SysRoleMenuEntity;
import com.dj.system.model.SysUserRoleEntity;
import com.dj.system.service.SysRoleMenuService;
import com.dj.system.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wxl
 * @date 2020-03-17 上午9:30
 * @company www.dfdk.com.cn
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private BaseController baseController;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    public SysRoleServiceImpl(SysRoleMenuService sysRoleMenuService) {
        this.sysRoleMenuService = sysRoleMenuService;
    }

    @Override
    public boolean addRole(SysRoleEntity roleEntity, Long[] menuIds) {

        if (menuIds.length <= 0) {
            int row = this.baseMapper.insert(roleEntity);
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            List<SysRoleMenuEntity> roleMenuEntityList = new ArrayList<>();
            Arrays.asList(menuIds)
                    .forEach(m -> roleMenuEntityList.add(new SysRoleMenuEntity(roleEntity.getRoleId(),m)));

            boolean flag = this.save(roleEntity);
            if (flag) {
                boolean b = sysRoleMenuService.saveBatch(roleMenuEntityList);
                if (b) {
                    return true;
                }
            }
            return false;
        }

    }

    @Override
    public boolean updateRole(SysRoleEntity roleEntity, Long[] menuIds) {

        Long roleId = roleEntity.getRoleId();
        int len = menuIds.length;
        if (len <= 0) {
            int row = this.baseMapper.updateById(roleEntity);
            return row >= 0;
        } else {
            sysRoleMenuService.remove(new QueryWrapper<SysRoleMenuEntity>().lambda().eq(SysRoleMenuEntity::getRoleId,roleId));
            List<SysRoleMenuEntity> roleMenuEntityList = new ArrayList<>();
            Arrays.asList(menuIds)
                    .forEach(m -> roleMenuEntityList.add(new SysRoleMenuEntity(roleEntity.getRoleId(),m)));
            int row = this.baseMapper.updateById(roleEntity);
            if (row < 0) {
                return false;
            } else {
                sysRoleMenuService.saveBatch(roleMenuEntityList);
                return true;
            }
        }
    }

    @Override
    public List<T> queryRoleList() {
        //获取操作用户类型
        String sysUserType = baseController.getSysUserType();
        //如果是超级管理员，返回所有的角色
        if ("1".equals(sysUserType)){
            return baseMapper.queryRoleList();
        }

        List<T> sysRoleList = baseMapper.queryOtherList();
        return sysRoleList;

    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ResEntity<?> saveOrUpdateSysRole(SysRoleRequest sysRoleRequest) {
        List<SysRoleMenuEntity> sysRoleMenuEntityList = new ArrayList<>();
        Long[] menuId = sysRoleRequest.getMenuIds();
        // 新增
        if (sysRoleRequest.getRoleId() == null) {
            // 新增角色
            SysRoleEntity sysRoleEntity = new SysRoleEntity();
            sysRoleEntity.setRoleKey(sysRoleRequest.getRoleKey());
            sysRoleEntity.setRoleName(sysRoleRequest.getRoleName());
            sysRoleEntity.setStatus(sysRoleRequest.getStatus());
            sysRoleEntity.setDelFlag(0);
            this.baseMapper.insert(sysRoleEntity);

            for (Long id : menuId) {
                SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity(sysRoleEntity.getRoleId(), id);
                sysRoleMenuEntityList.add(sysRoleMenuEntity);
            }
            sysRoleMenuService.saveBatch(sysRoleMenuEntityList);
            return ResEntity.SUCCESS();
        }
        sysRoleMenuService.removeByRoleId(Collections.singletonList(sysRoleRequest.getRoleId()));
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        sysRoleEntity.setRoleKey(sysRoleRequest.getRoleKey());
        sysRoleEntity.setRoleId(sysRoleRequest.getRoleId());
        sysRoleEntity.setRoleName(sysRoleRequest.getRoleName());
        sysRoleEntity.setStatus(sysRoleRequest.getStatus());
        int updateCount = this.baseMapper.updateById(sysRoleEntity);
        if (updateCount > 0) {
            for (Long id : menuId) {
                SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity(sysRoleRequest.getRoleId(), id);
                sysRoleMenuEntityList.add(sysRoleMenuEntity);
            }
            sysRoleMenuService.saveBatch(sysRoleMenuEntityList);
        }
        return ResEntity.SUCCESS();
    }

    @Override
    public SysRoleEntity getSysRoleByRoleName(String roleName) {
        LambdaQueryWrapper<SysRoleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRoleEntity::getRoleName, roleName);
        return this.baseMapper.selectOne(lambdaQueryWrapper);
    }

    /**
     * 删除角色，软删除
     * @param sysRoleEntity
     * @return
     */
    @Override
    public ResEntity<?> deleteSysRole(SysRoleEntity sysRoleEntity) {
        //先查询该角色是否已经授予用户
        LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRoleEntity::getRoleId, sysRoleEntity.getRoleId());
        int count = sysUserRoleMapper.selectCount(queryWrapper);
        if (count > 0){
            return ResEntity.ERROR("该角色已经授予给用户,删除失败");
        }
        //获取登陆用户类型
        String sysUserType = baseController.getSysUserType();
        //如果操作用户是超级管理员
        if (sysUserType.equals("1")){
            baseMapper.deleteById(sysRoleEntity.getRoleId());
            sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenuEntity>().eq(SysRoleMenuEntity::getRoleId,sysRoleEntity.getRoleId()));
            return ResEntity.SUCCESS("删除角色成功");
        }
        //如果不是超级用户，将角色进行软删除
        SysRoleEntity roleEntity = new SysRoleEntity();
        roleEntity.setDelFlag(2);
        this.baseMapper.update(roleEntity,new QueryWrapper<SysRoleEntity>().eq("role_id",sysRoleEntity.getRoleId()));
        return ResEntity.SUCCESS("删除成功");

    }

    @Override
    public ResEntity<?> queryRolePage(QuerySysRoleRequest request) {
        IPage<SysRoleEntity> page = new Page<>(request.getCurrentPage(), request.getPageSize());

        LambdaQueryWrapper<SysRoleEntity> roleEntityQueryWrapper = new LambdaQueryWrapper<>();
        //获取用户类型
        String userType = baseController.getSysUserType();
        //用户是超级管理员，查询所有（包含软删除的）
        if (userType.equals("1")){
            roleEntityQueryWrapper
                    .like(StringUtils.isNotEmpty(request.getRoleName()), SysRoleEntity::getRoleName,request.getRoleName());
        }else {//不是，查询部分（不包含软删除）
            roleEntityQueryWrapper
                    .eq(SysRoleEntity::getDelFlag,0)
                    .like(StringUtils.isNotEmpty(request.getRoleName()), SysRoleEntity::getRoleName,request.getRoleName());
        }

        IPage<SysRoleEntity> selectPage = this.baseMapper.selectPage(page, roleEntityQueryWrapper);
        return ResEntity.OK(selectPage.getRecords(), Integer.parseInt(selectPage.getTotal() + ""));
    }

}
