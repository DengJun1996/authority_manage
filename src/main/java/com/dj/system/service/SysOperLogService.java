package com.dj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.common.QuerySysLogRequest;
import com.dj.common.ResEntity;
import com.dj.system.model.SysOperLogEntity;
import com.dj.system.vo.ResVo;

/**
 * 系统日志抽象类
 */
public interface SysOperLogService extends IService<SysOperLogEntity> {


    ResVo<SysOperLogEntity> page(SysOperLogEntity operLogEntity, Long currentPage, Long countPage);

    /**
     * 分页查询系统日志
     * @param request
     * @return
     */
    ResEntity<?> querySysLogPage(QuerySysLogRequest request);
}
