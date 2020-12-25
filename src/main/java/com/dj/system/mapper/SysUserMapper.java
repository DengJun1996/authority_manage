package com.dj.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
