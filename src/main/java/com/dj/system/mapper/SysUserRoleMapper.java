package com.dj.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.system.model.SysMenuEntity;
import com.dj.system.model.SysUserRoleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxl
 * @date 2020-03-19 上午10:16
 * @company www.dfdk.com.cn
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

    List<SysMenuEntity> selectMenuByRoleId(@Param("loginName") String loginName);
}
