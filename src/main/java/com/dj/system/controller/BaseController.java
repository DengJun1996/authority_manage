package com.dj.system.controller;

import com.dj.config.JWTHelper;
import com.dj.config.ConfigProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础控制层
 * @author jetlag
 * @date 2020/6/24
 **/
@Controller
public class BaseController {

    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ConfigProperties configProperties;

    /**
     * 获取系统用户登录名
     * @return
     */
    public String getSysUserLoginName() {
        return jwtHelper.getLoginName(getToken());
    }

    /**
     * 获取token
     * @return
     */
    private String getToken() {
        String header = request.getHeader(configProperties.getJwt().getHeader());
        String prefix = configProperties.getJwt().getPrefix();
        return StringUtils.removeStart(header, prefix);
    }
}
