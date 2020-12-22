package com.dj.authority_manage.controller;

import com.dj.authority_manage.common.ResEntity;
import com.dj.authority_manage.service.system.SysUserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dj
 * @date 2020-12-21 16:49
 */

@Controller
public class indexController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequestMapping("/index")
    public String sayHello(){
        return "index";
    }

    @RequestMapping("/login")
    public String login () {
        return "login";
    }

    @RequestMapping(value = {"/main"})
    public String main() {
        return "main";
    }

    /**
     * 初始化菜单接口
     *
     * @return
     * @author dj
     * @date 2020-06-22 15:54
     */
    @ApiOperation("初始化菜单接口")
    @PostMapping(value = "/indexMenu")
    @ResponseBody
    public ResEntity<?> indexMenu() {
        return ResEntity.SUCCESS(sysUserRoleService.indexMenu(getSysUserLoginName()));
    }



}
