package com.dj.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.system.model.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxl
 * @date 2020-03-19 上午10:19
 * @company www.dfdk.com.cn
 */
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {
    List<SysRoleMenuEntity> selectarrayMenuId(@Param("roleId") Long roleId);
}
