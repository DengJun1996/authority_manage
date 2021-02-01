package com.dj.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wxl
 * @Description:
 * @date 2020/7/2
 * @company www.dfdk.com.cn
 */
@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "dfdk")
public class ConfigProp {

    private String url;
    private Integer port;
    private String topic;
    private String areaApi;
}
