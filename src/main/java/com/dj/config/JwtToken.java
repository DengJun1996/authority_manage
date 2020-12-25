package com.dj.config;

import lombok.AllArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author jetlag
 * @date 2020/5/8
 **/
@AllArgsConstructor
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = -5603473537088931282L;

    private String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
