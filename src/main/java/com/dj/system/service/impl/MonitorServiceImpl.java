package com.dj.system.service.impl;

import com.dj.common.ServerScheduledTask;
import com.dj.system.model.monitor.ServerEntity;
import com.dj.system.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxl
 * @date 2020-03-19 下午5:27
 * @company www.dfdk.com.cn
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private ServerScheduledTask scheduledTask;


    @Override
    public ServerEntity get() {
        return scheduledTask.getServerEntity();
    }

}
