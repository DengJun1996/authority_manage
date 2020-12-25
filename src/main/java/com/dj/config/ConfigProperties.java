package com.dj.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author jetlag
 * @date 2020/4/27
 **/
@Getter
@Configuration
@ConfigurationProperties(prefix = "dn")
public class ConfigProperties {
    private JwtProperties jwt = new JwtProperties();

}
