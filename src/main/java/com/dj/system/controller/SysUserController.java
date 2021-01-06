package com.dj.system.controller;

import com.dj.common.QuerySysUserRequest;
import com.dj.common.ResEntity;
import com.dj.common.SysUserRequest;
import com.dj.system.model.SysUserEntity;
import com.dj.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wxl
 * @date 2020-03-18 上午11:23
 * @company www.dfdk.com.cn
 */
@Api(tags = "系统用户API")
@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "添加用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户实体类", name = "SysUserEntity", required = true),
            @ApiImplicitParam(value = "岗位ID", name = "postId", required = true, dataType = "Long"),
            @ApiImplicitParam(value = "角色ID", name = "roleId", required = true, dataType = "Long"),
            @ApiImplicitParam(value = "部门ID", name = "deptId", required = true, dataType = "Long"),
            @ApiImplicitParam(value = "1:代表添加;2:代表修改", name = "type", required = true)
    })
    @PostMapping("/addOrUpdateUserPage")
    @ResponseBody
    public ResEntity<?> addUsePage(@Validated SysUserRequest sysUserRequest) {
        return sysUserService.saveOrUpdateSysUser(sysUserRequest);
    }


    @ApiOperation(value = "获取所有的分页查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页码", required = true),
            @ApiImplicitParam(name = "countPage", value = "每页显示的条数", required = true),
            @ApiImplicitParam(value = "用户实体类", name = "SysUserEntity", required = true)
    })
    @GetMapping("/listUser")
    @ResponseBody
    public ResEntity<?> queryUserList(@Validated QuerySysUserRequest request) {
        return sysUserService.querySysUserPage(request);
    }

    @ApiOperation(value = "修改用户密码", httpMethod = "POST")
    @ApiImplicitParam(value = "用户实体类", name = "SysUserEntity", required = true)
    @PostMapping("/modifyPwd")
    @ResponseBody
    public ResEntity modifyPwd(SysUserEntity sysUserEntity) {
        return new ResEntity(sysUserService.updatePwd(sysUserEntity));
    }

    @ApiOperation(value = "重值用户密码", httpMethod = "POST")
    @ApiImplicitParam(value = "用户实体类", name = "SysUserEntity", required = true)
    @PostMapping("/resetPwd")
    @ResponseBody
    public ResEntity resetPwd(SysUserEntity sysUserEntity) {
        return new ResEntity(sysUserService.updatePwd(sysUserEntity));
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping("/delSysUser")
    @ResponseBody
    public ResEntity delSysUser(SysUserEntity sysUserEntity){
        return sysUserService.deleteSysUser(sysUserEntity);
    }

}
