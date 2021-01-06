package com.dj.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.system.model.SysDeptEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxl
 * @date 2020-03-19 上午10:16
 * @company www.dfdk.com.cn
 */
@Repository
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {

    List<SysDeptEntity> querydeptList();

    List<T> selectDetpNam(@Param("deptId") String deptId);
}
