package com.dj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.common.QuerySysLogRequest;
import com.dj.common.ResEntity;
import com.dj.system.mapper.SysOperLogMapper;
import com.dj.system.model.SysOperLogEntity;
import com.dj.system.service.SysOperLogService;
import com.dj.system.vo.ResVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author wxl
 * @date 2020-03-19 上午10:22
 * @company www.dfdk.com.cn
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLogEntity> implements SysOperLogService {


    @Override
    public ResVo<SysOperLogEntity> page(SysOperLogEntity operLogEntity, Long currentPage, Long countPage) {
        IPage<SysOperLogEntity> iPage = new Page<>(currentPage,countPage);
        QueryWrapper<SysOperLogEntity> deptEntityQueryWrapper = new QueryWrapper<>();
        deptEntityQueryWrapper.lambda()
                .like(StringUtils.isNotEmpty(operLogEntity.getOperName()),SysOperLogEntity::getOperName,operLogEntity.getOperName())
                .like(StringUtils.isNotEmpty(operLogEntity.getDeptName()),SysOperLogEntity::getDeptName,operLogEntity.getDeptName())
                .like(StringUtils.isNotEmpty(operLogEntity.getOperUrl()),SysOperLogEntity::getOperUrl,operLogEntity.getOperUrl());
        return new ResVo<>(this.baseMapper.selectPage(iPage,deptEntityQueryWrapper));
    }

    @Override
    public ResEntity<?> querySysLogPage(QuerySysLogRequest request) {
        IPage<SysOperLogEntity> iPage = new Page<>(request.getCurrentPage(), request.getPageSize());
        LambdaQueryWrapper<SysOperLogEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .like(StringUtils.isNotEmpty(request.getTitle()), SysOperLogEntity::getTitle, request.getTitle());
        IPage<SysOperLogEntity> selectPage = this.baseMapper.selectPage(iPage, lambdaQueryWrapper);

        return ResEntity.OK(selectPage.getRecords(), Integer.parseInt(selectPage.getTotal() + ""));

    }


}
