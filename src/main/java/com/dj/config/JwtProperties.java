package com.dj.config;

import lombok.Data;

/**
 * @author jetlag
 * @date 2020/5/11
 **/
@Data
public class JwtProperties {

    /** 头信息*/
    private String header;

    /** 前缀*/
    private String prefix = "Bearer ";

    /** 过期时长*/
    private long expiration;

    /**
     * redis中过期时间
     */
    private long redisExpiration;
}
