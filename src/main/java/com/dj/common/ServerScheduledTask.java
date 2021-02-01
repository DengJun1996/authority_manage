package com.dj.common;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.dj.system.model.monitor.ServerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定时服务类
 * @author wxl
 * @date 2020-03-20 上午9:51
 * @company www.dfdk.com.cn
 */
@Component
public class ServerScheduledTask {

    private static Logger logger = LoggerFactory.getLogger(ServerScheduledTask.class);

    private static final AtomicInteger ON_LINE_COUNT = new AtomicInteger(0);

    public static Cache<String, ServerEntity> serverEntityCache = CacheUtil.newFIFOCache(3);

    private static final String KEY = "SERVER";

    @Scheduled(initialDelay = 1000,fixedDelay = 30000)
    public void taskMonitorServer () {
        try {
            ServerEntity serverEntity = new ServerEntity();
            serverEntity.copyTo();
            serverEntityCache.put(KEY + ON_LINE_COUNT.incrementAndGet(),serverEntity);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    public ServerEntity getServerEntity () {
        synchronized (serverEntityCache){
            if (serverEntityCache == null) {
                ServerEntity serverEntity = new ServerEntity();
                try {
                    serverEntity.copyTo();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                serverEntityCache.put(KEY + ON_LINE_COUNT.incrementAndGet(),serverEntity);
                return serverEntity;
            } else {
                ServerEntity serverEntity = serverEntityCache.get(KEY + getCount());
                return serverEntity;
            }
        }
    }


    /**
     * 获取自增
     * @author wxl
     * @date 2020-03-20
     * @return
     **/
    private int getCount() {
        return ON_LINE_COUNT.get();
    }


}
