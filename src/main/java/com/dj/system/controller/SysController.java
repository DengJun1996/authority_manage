package com.dj.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统所有页面请求控制器
 * @author wxl
 * @date 2020-03-17 上午10:32
 * @company www.dfdk.com.cn
 */
@Controller
public class SysController {

    private static final String PREFIX = "page/sys/";

    @RequestMapping("/sysUser")
    public String user () {
        return PREFIX + "user";
    }

    @RequestMapping("/sysRole")
    public String role () {
        return PREFIX + "role";
    }

    @RequestMapping("/sysMenu")
    public String menu () {
        return PREFIX + "menu";
    }

    @RequestMapping("/sysPost")
    public String post () {
        return PREFIX + "post";
    }

    @RequestMapping("/sysLog")
    public String log () {
        return PREFIX + "log";
    }

    @RequestMapping("/sysDept")
    public String sysDept () {
        return PREFIX + "dept";
    }

    @RequestMapping("/sys")
    public String sys () {
        return PREFIX + "sys";
    }

    @RequestMapping("/addUserPage")
    public String addUserPage () {
        return PREFIX + "vo/addUser";
    }

    @RequestMapping("/addRolePage")
    public String addRolePage () {
        return PREFIX + "vo/addRole";
    }

    @RequestMapping("/addMenuPage")
    public String addMenuPage () {
        return PREFIX + "vo/addMenu";
    }

    @RequestMapping("/addDeptPage")
    public String addDeptPage () {
        return PREFIX + "vo/addDept";
    }

    @RequestMapping("/addPostPage")
    public String addPostPage () {
        return PREFIX + "vo/addPost";
    }

    @RequestMapping("/editDeptPage")
    public String editDeptPage () {
        return PREFIX + "vo/editDept";
    }

    @RequestMapping("/editPostPage")
    public String editPostPage () {
        return PREFIX + "vo/editPost";
    }

    @RequestMapping("/editMenuPage")
    public String editMenuPage () {
        return PREFIX + "vo/editMenu";
    }

    @RequestMapping("/editUserPage")
    public String editUserPage () {
        return PREFIX + "vo/editUser";
    }

    @RequestMapping("/editRolePage")
    public String editRolePage () {
        return PREFIX + "vo/editRole";
    }

    @RequestMapping("/login")
    public String login () {
        return "login";
    }
}
