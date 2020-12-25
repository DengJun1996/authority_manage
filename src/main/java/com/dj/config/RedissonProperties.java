package com.dj.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jetlag
 * @date 2020/8/18
 **/
@Data
@ConfigurationProperties(prefix = "dn.redisson")
public class RedissonProperties {

    /**
     * redission配置路径
     */
    private String path;
}
