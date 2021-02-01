package com.dj.system.controller;

import com.dj.common.ResEntity;
import com.dj.system.service.MonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wxl
 * @date 2020-03-18 下午2:37
 * @company www.dfdk.com.cn
 */
@Api(tags = "监控服务器及系统API")
@RestController
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @ApiOperation(value = "获取监控服务器")
    @CrossOrigin("*")
    @GetMapping("/serverMonitor")
    public ResEntity<?> serverMonitor() {
        return ResEntity.SUCCESS(monitorService.get());
    }


}
