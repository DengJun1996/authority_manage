package com.dj.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author jetlag
 * @date 2020/4/29
 **/
@Configuration
@ConditionalOnClass(RedissonClient.class)
@AutoConfigureBefore(RedisAutoConfiguration.class)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {

    private RedissonProperties redissonProperties;

    private ApplicationContext applicationContext;

    public RedissonAutoConfiguration(RedissonProperties redissonProperties,
                                     ApplicationContext applicationContext) {
        this.redissonProperties = redissonProperties;
        this.applicationContext = applicationContext;
    }

    /**
     * 加载redisson配置
     * @return
     * @throws IOException
     */
    @ConditionalOnMissingBean
    @Bean(destroyMethod="shutdown")
    public RedissonClient redissonClient() throws IOException {
        Resource resource = applicationContext.getResource(redissonProperties.getPath());
        Config config = Config.fromYAML(resource.getInputStream());
//        if (config.getCodec() == null) {
//            config.setCodec(new JsonJacksonCodec());
//        }
        return Redisson.create(config);
    }
}
