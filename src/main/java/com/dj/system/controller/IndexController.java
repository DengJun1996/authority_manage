package com.dj.system.controller;


import com.dj.common.ResEntity;
import com.dj.system.service.SysUserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wxl
 * @date 2020-03-04 上午8:33
 * @company www.dfdk.com.cn
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequestMapping(value = {"/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/main"})
    public String main() {
        return "main";
    }

    @RequestMapping("/swagger")
    public String swagger() {
        return "redirect:/swagger-ui.html";
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
