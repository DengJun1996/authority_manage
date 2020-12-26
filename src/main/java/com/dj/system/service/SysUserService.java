package com.dj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.common.ResEntity;
import com.dj.system.model.SysRoleEntity;
import com.dj.system.model.SysUserEntity;

import com.dj.system.vo.SysUserVo;

import java.util.List;

/**
 * @author wxl
 * @date 2020-03-17 上午9:24
 * @company www.dfdk.com.cn
 */
public interface SysUserService extends IService<SysUserEntity> {
    /**
     * 根据用户名查找
     *
     * @param userName
     * @return
     * @author wxl
     * @date 2020-03-20
     **/
    SysUserEntity getByUserName(String userName);
    /**
     * 用户登录接口
     *
     * @param sysUserVo
     * @return
     */
    ResEntity<?> tokenLogin(SysUserVo sysUserVo);

    /**
     * 请求头数据
     *
     * @param userId
     * @return
     */
    List<SysRoleEntity> getUserRoles(Long userId);

}
