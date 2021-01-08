package com.dj.system.controller;

import com.dj.common.ResEntity;
import com.dj.common.SysRoleRequest;
import com.dj.system.model.SysRoleEntity;
import com.dj.system.service.SysRoleMenuService;
import com.dj.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author wxl
 * @date 2020-03-19 上午8:49
 * @company www.dfdk.com.cn
 */
@Api(tags = "角色API")
@Controller
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 添加或者修改角色
     * @param sysRoleRequest
     * @return
     */
    @ApiOperation(value = "添加或者修改角色", httpMethod = "POST")
    @PostMapping(value = "/addOrUpdateSysRole")
    @ResponseBody
    public ResEntity<?> addOrUpdateSysRole(@Validated SysRoleRequest sysRoleRequest) {
        return sysRoleService.saveOrUpdateSysRole(sysRoleRequest);
    }

    @ApiOperation(value = "获取所有的分页查询", httpMethod = "GET")
    @GetMapping(value = "/roleListPage")
    @ResponseBody
    public ResEntity<?> roleListPage(@Validated QuerySysRoleRequest request) {
        return sysRoleService.queryRolePage(request);
    }

    @RequestMapping("/delSysRole")
    @ResponseBody
    public ResEntity<?> delSysRole(SysRoleEntity sysRoleEntity) {
       return sysRoleService.deleteSysRole(sysRoleEntity);
    }


    @ApiOperation(value = "按角色菜单", httpMethod = "GET")
    @GetMapping(value = "/getRoleName")
    @ResponseBody
    public ResEntity roleList() {
        return new ResEntity(sysRoleService.queryRoleList());
    }


    /**
     * 获取所有的菜单（树形结构）
     */
    @RequestMapping(value = "/listRoleToMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResEntity listRoleToMenu() {
        return new ResEntity(sysRoleMenuService.listAllMenu());
    }

    /**
     * 根据角色id查询拥有的菜单
     *
     * @param roleId
     * @return
     */
    @PostMapping("/listMenuByRoleId")
    @ResponseBody
    public ResEntity listMenuByRoleId(@RequestParam("roleId") Long roleId) {
        return new ResEntity(sysRoleMenuService.listMenuByRoleID(roleId));
    }

}
