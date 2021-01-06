package com.dj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.common.QuerySysDeptRequest;
import com.dj.common.ResEntity;
import com.dj.system.model.SysDeptEntity;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author wxl
 * @date 2020-03-19 上午10:22
 * @company www.dfdk.com.cn
 */
public interface SysDeptService extends IService<SysDeptEntity> {

    ResEntity<?> saveDept(SysDeptEntity sysDeptEntity);

    ResEntity<?> updateDept(SysDeptEntity sysDeptEntity);

    ResEntity<?> removeByDeptId(Integer deptId);

    List<Object> querydeptList();

    /**
     * 部门分页查询
     * @param request
     * @return
     */
    ResEntity<?> querySysDeptPage(QuerySysDeptRequest request);

    ResEntity<?> sysDeptList();

    List<T> selectDeptNameById(String deptId);
}
