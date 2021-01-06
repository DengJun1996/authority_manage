package com.dj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.common.QuerySysDeptRequest;
import com.dj.common.ResEntity;
import com.dj.system.mapper.SysDeptMapper;
import com.dj.system.mapper.SysUserMapper;
import com.dj.system.model.SysDeptEntity;
import com.dj.system.model.SysUserEntity;
import com.dj.system.service.SysDeptService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxl
 * @date 2020-03-19 上午10:22
 * @company www.dfdk.com.cn
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements SysDeptService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public ResEntity<?> saveDept(SysDeptEntity sysDeptEntity) {
        //部门名称不能重复
        Integer count = this.baseMapper.selectCount(new LambdaQueryWrapper<SysDeptEntity>().eq(SysDeptEntity::getDeptName, sysDeptEntity.getDeptName()));
        if (count > 0){
            return ResEntity.ERROR("该部门名称已存在，请重新输入");
        }
        this.baseMapper.insert(sysDeptEntity);
        return ResEntity.SUCCESS("新增部门成功");
    }

    @Override
    public ResEntity<?> updateDept(SysDeptEntity sysDeptEntity) {
        SysDeptEntity deptEntity = this.baseMapper.selectOne(new LambdaQueryWrapper<SysDeptEntity>().eq(SysDeptEntity::getDeptName, sysDeptEntity.getDeptName()));
        if (deptEntity != null && !deptEntity.getDeptId().equals(sysDeptEntity.getDeptId())){
            return ResEntity.ERROR("该部门名称已存在，请重新输入");
        }
        if (sysDeptEntity.getPid()!=null){
            if (sysDeptEntity.getPid().equals(sysDeptEntity.getDeptId())){
                return ResEntity.ERROR("父部门不能是自己");
            }
        }
        this.baseMapper.updateById(sysDeptEntity);
        return ResEntity.SUCCESS("更新部门成功");
    }

    /**
     * 删除部门
     *
     * @param deptId
     * @return
     */
    @Override
    public ResEntity<?> removeByDeptId(Integer deptId) {
        //查询是否存在下级部门
        Integer countDept = this.baseMapper.selectCount(new LambdaQueryWrapper<SysDeptEntity>().eq(SysDeptEntity::getPid, deptId));
        if (countDept > 0){
            return ResEntity.ERROR("该部门存在下级部门，不允许删除");
        }
        //先查询是否有用户和部门有关联
        Integer countSysUser = sysUserMapper.selectCount(new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getDeptId, deptId));
        if (countSysUser > 0){
            return ResEntity.ERROR("部门存在用户，不允许删除");
        }
        this.baseMapper.deleteById(deptId);
        return ResEntity.SUCCESS("删除部门成功");
    }

    @Override
    public List<Object> querydeptList() {
        List<SysDeptEntity> deptEntityList = this.baseMapper.selectList(new LambdaQueryWrapper<SysDeptEntity>().eq(SysDeptEntity::getPid, "0"));
        return excuteDept(deptEntityList);

    }

    public List<Object> excuteDept(List<SysDeptEntity> list){
        List<Object> listAllDept = new ArrayList<>();
        for (SysDeptEntity entity: list
             ) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",entity.getDeptId());
            map.put("title",entity.getDeptName());

            List<SysDeptEntity> deptList = this.baseMapper.selectList(new LambdaQueryWrapper<SysDeptEntity>().eq(SysDeptEntity::getPid, entity.getDeptId()));
            if (deptList != null){
                map.put("children",excuteDept(deptList));
            }
            listAllDept.add(map);
        }
        return listAllDept;
    }

    @Override
    public ResEntity<?> querySysDeptPage(QuerySysDeptRequest request) {
        IPage<SysDeptEntity> page = new Page<>(request.getCurrentPage(), request.getPageSize());
        LambdaQueryWrapper<SysDeptEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(request.getDeptName()), SysDeptEntity::getDeptName, request.getDeptName());
        IPage<SysDeptEntity> selectPage = this.baseMapper.selectPage(page, lambdaQueryWrapper);

        return ResEntity.OK(selectPage.getRecords(), Integer.parseInt(selectPage.getTotal() + ""));
    }

    @Override
    public ResEntity<?> sysDeptList() {
        List<SysDeptEntity> sysDeptEntities = this.baseMapper.querydeptList();
        return ResEntity.OK(sysDeptEntities);
    }

    @Override
    public List<T> selectDeptNameById(String deptId) {
        return this.baseMapper.selectDetpNam(deptId);
    }


}
