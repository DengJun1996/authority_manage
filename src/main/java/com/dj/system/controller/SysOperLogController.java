package com.dj.system.controller;

import com.dj.annotation.SysLog;
import com.dj.common.QuerySysLogRequest;
import com.dj.common.ResEntity;
import com.dj.system.model.SysOperLogEntity;
import com.dj.system.service.SysOperLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jcj
 * @date 2020-03-20 上午8：53
 * @company www.dfdk.com.cn
 */
@Api(tags = "系统日志操作API")
@Controller
public class SysOperLogController {

    @Autowired
    private SysOperLogService sysOperLogService;

    @SysLog(title = "添加或修改日志操作")
    @ApiOperation(value = "修改日志操作",httpMethod = "POST")
    @ApiImplicitParam(value = "日志操作实体类",name = "SysOperLogEntity",required = true)
    @PostMapping("/addoOrUpdateSysOperLog")
    @ResponseBody
    public ResEntity updateSysDept (SysOperLogEntity sysOperLogEntity) {
        return new ResEntity(sysOperLogService.saveOrUpdate(sysOperLogEntity));
    }
    @SysLog(title = "获取所有的分页查询")
    @ApiOperation(value = "获取所有的分页查询",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage",value = "当前页码",required = true),
            @ApiImplicitParam(name = "countPage",value = "每页显示的条数",required = true),
            @ApiImplicitParam(value = "日志操作实体类",name = "SysOperLogEntity",required = true)
    })
    @GetMapping("/listOperLog")
    @ResponseBody
    public ResEntity<?> querySysLogPage (@Validated QuerySysLogRequest request) {


        return sysOperLogService.querySysLogPage(request);

    }



}
