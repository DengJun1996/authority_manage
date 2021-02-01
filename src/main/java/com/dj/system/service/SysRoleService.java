package com.dj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.common.ResEntity;
import com.dj.common.SysRoleRequest;
import com.dj.common.QuerySysRoleRequest;
import com.dj.system.model.SysRoleEntity;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author wxl
 * @date 2020-03-17 上午9:30
 * @company www.dfdk.com.cn
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    boolean addRole(SysRoleEntity roleEntity, Long[] menuIds);

    boolean updateRole(SysRoleEntity roleEntity, Long[] menuIds);

    List<T> queryRoleList();

    /**
     * 新增或者更新角色信息
     * @param sysRoleRequest 角色请求参数
     * @return
     */
    ResEntity<?> saveOrUpdateSysRole(SysRoleRequest sysRoleRequest);

    /**
     * 根据角色名称查询
     * @param roleName 角色名称
     * @return
     */
    SysRoleEntity getSysRoleByRoleName(String roleName);

    ResEntity<?> deleteSysRole(SysRoleEntity sysRoleEntity);

    /**
     * 查询用户角色分页
     * @param request
     * @return
     */
    ResEntity<?> queryRolePage(QuerySysRoleRequest request);
}
