package com.dj.system.controller;

import com.dj.common.MenuTypeEnum;
import com.dj.common.QuerySysMenuRequest;
import com.dj.common.ResEntity;
import com.dj.system.model.SysMenuEntity;
import com.dj.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author wxl
 * @date 2020-03-17 下午2:51
 * @company www.dfdk.com.cn
 */
@Api(tags = "系统菜单API")
@Controller
public class SysMenuController extends BaseController {

    private SysMenuService sysMenuService;

    public SysMenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @ApiOperation(value = "添加菜单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "菜单实体类", name = "SysMenuEntity", required = true)
    })
    @PostMapping("/addOrUpdateSysMenu")
    @ResponseBody
    public ResEntity<?> addSysMenu(SysMenuEntity sysMenuEntity) {
        getSysUserLoginName();
        return sysMenuService.saveOrUpdateMenu(sysMenuEntity);
    }

    @ApiOperation(value = "查询所有菜单", httpMethod = "GET")
    @GetMapping(value = "/querySysMenuPage")
    @ResponseBody
    public ResEntity<?> querySysMenuPage(@Validated QuerySysMenuRequest request) {
        return sysMenuService.querySysMenuPage(request);
    }

    /**
     * 删除菜单(只有超级管理员才能执行删除操作)
     *
     * @param sysMenuEntity
     * @return
     */
    @RequestMapping("/delSysMenu")
    @ResponseBody
    public ResEntity<?> delSysMenu(SysMenuEntity sysMenuEntity) {
        return sysMenuService.removeByMenuId(sysMenuEntity.getMenuId());
    }

    /**
     * 根据菜单名称模糊查询菜单
     *
     * @param menuName
     * @return
     */
    @ApiOperation(value = "按菜单名称查找", httpMethod = "GET")
    @GetMapping("/getByMenuName")
    @ResponseBody
    public ResEntity<?> getByMenuName(@RequestParam("menuName") String menuName) {
        return new ResEntity(sysMenuService.getByMenuName(menuName));
    }

    /**
     * 根据菜单类型查询对应菜单
     *
     * @param menuType 菜单类型（M目录 C菜单 F按钮）
     * @return
     */
    @ApiOperation(value = "showMenuTree")
    @GetMapping(value = "/showMenuTree")
    @ResponseBody
    public ResEntity<?> showMenuTree(String menuType) {
        if (StringUtils.isBlank(menuType)) {
            return ResEntity.ERROR("参数不能为空");
        }
        return sysMenuService.getSupMenuTree(MenuTypeEnum.getMenuTypeEnum(menuType));
    }


}
