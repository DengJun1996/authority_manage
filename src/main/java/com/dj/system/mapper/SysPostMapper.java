package com.dj.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.system.model.SysPostEntity;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxl
 * @date 2020-03-19 上午10:15
 * @company www.dfdk.com.cn
 */
@Repository
public interface SysPostMapper extends BaseMapper<SysPostEntity> {

    List<T> queryPostList();
}
