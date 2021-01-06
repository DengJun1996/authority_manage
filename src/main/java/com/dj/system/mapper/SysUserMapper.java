package com.dj.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.common.QuerySysUserRequest;
import com.dj.system.model.SysUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxl
 * @date 2020-03-17 上午9:22
 * @company www.dfdk.com.cn
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    /**
     * 根据登录名 部门名称 手机号查询用户信息
     * @param loginName
     * @param deptName
     * @param phoneNumber
     * @return
     */
    List<T> queryUserList(@Param("loginName") String loginName, @Param("deptName") String deptName, @Param("phoneNumber") String phoneNumber, String userType);

    List<SysUserEntity> queryUserPage(@Param("request") QuerySysUserRequest request);

    int selectUserCount(@Param("request") QuerySysUserRequest request);

    int selectAdminUserCount(@Param("request") QuerySysUserRequest request);

    List<SysUserEntity> queryAdminUserList(@Param("request") QuerySysUserRequest request);

}
