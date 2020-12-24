package com.dj.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dj.exception.DnJwtException;
import com.dj.system.model.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

/**
 * jwt帮助类
 * @author jetlag
 * @date 2020/05/11
 */
@Component
public class JWTHelper {

    private static final Logger log = LoggerFactory.getLogger(JWTHelper.class);

    private ConfigProperties configProperties;

    public JWTHelper(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    private static final String DN_LOGIN_NAME = "loginName";

    private static final String DN_USER_ID = "userId";

    private static final String DN_USER_TYPE = "userType";
    /**
     * 生成token
     * @return
     */
    public String generateToken(SysUserEntity sysUserEntity) {
        Date now = Date.from(Instant.now());
        long expiration = configProperties.getJwt().getExpiration();
        Date expiresAt = Date.from(ZonedDateTime.now()
                                                .plusMinutes(expiration)
                                                .toInstant());
       return JWT.create()
               .withClaim(DN_USER_ID, sysUserEntity.getUserId())
               .withClaim(DN_USER_TYPE, sysUserEntity.getUserType())
               .withClaim(DN_LOGIN_NAME, sysUserEntity.getLoginName())
               .withSubject(sysUserEntity.getUserName())
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(sysUserEntity.getPassword()));
    }
    
    /**
     * 验证token
     * @param token
     * @param secret
     * @return true/过期   false/正常
     */
    public boolean validateToken(String token, String secret) {
        try {
            if (token == null) {
                return false;
            }
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * token转化map
     * @param token token
     * @return
     */
    public Map<String, Claim> getAuthentication(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaims();
        } catch (DnJwtException e) {
            log.error("jwt获取Claims失败:{}", token);
            throw new DnJwtException("jwt获取Claims失败");
        }
    }

    /**
     * 获取用户账号
     * @param token token
     * @return
     */
    public String getLoginName(String token) {
        Map<String, Claim> authentication = getAuthentication(token);
        String loginName = authentication.get(DN_LOGIN_NAME).asString();
        Assert.notNull(loginName, "LOGIN_NAME IS ENPTY");
        return loginName;
    }

    /**
     * 获取用户类型
     * @param token token
     * @return
     */
    public String getUserType(String token) {
        Map<String, Claim> authentication = getAuthentication(token);
        String userType = authentication.get(DN_USER_TYPE).asString();
        Assert.notNull(userType, "登录类型为空");
        return userType;
    }

    /**
     * 获取用户userId
     * @param token token
     * @return
     */
    public Long getDnUserId(String token) {
        Map<String, Claim> authentication = getAuthentication(token);
        Long userId = authentication.get(DN_USER_ID).asLong();
        Assert.notNull(userId, "用户标识为空");
        return userId;
    }
}
