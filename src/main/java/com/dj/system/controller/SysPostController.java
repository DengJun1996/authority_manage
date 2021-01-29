package com.dj.system.controller;

import com.dj.annotation.SysLog;
import com.dj.common.QuerySysPostRequest;
import com.dj.common.ResEntity;
import com.dj.system.model.SysPostEntity;
import com.dj.system.service.SysPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author jcj
 * @date 2020-03-20 上午8：53
 * @company www.dfdk.com.cn
 */
@Api(tags = "系统岗位API")
@Controller
public class SysPostController {

    @Autowired
    private SysPostService sysPostService;
    @SysLog(title = "添加岗位信息")
    @ApiOperation(value = "添加岗位信息",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "岗位实体类",name = "SysPostEntity",required = true),
            @ApiImplicitParam(value = "1:代表添加;2:代表修改",name = "type",required = true)
    })
    @PostMapping("/addOrUpdatePost")
    @ResponseBody
    public ResEntity addOrUpdatePost (SysPostEntity sysPostEntity) {
        return new ResEntity(sysPostService.saveOrUpdate(sysPostEntity));
    }
    @SysLog(title = "获取所有的分页查询")
    @ApiOperation(value = "获取所有的分页查询",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage",value = "当前页码",required = true),
            @ApiImplicitParam(name = "countPage",value = "每页显示的条数",required = true),
            @ApiImplicitParam(value = "岗位实体类",name = "SysPostEntity",required = true)
    })
    @GetMapping("/listPost")
    @ResponseBody
    public ResEntity<?> querySysPostPage (@Validated QuerySysPostRequest request) {
        return sysPostService.querySysPostPage(request);

    }

    @RequestMapping("/delSysPost")
    @ResponseBody
    public ResEntity<?> delSysPost(SysPostEntity sysPostEntity){
        return  sysPostService.removeByPostId(sysPostEntity.getPostId());
    }

    @ApiOperation(value = "按岗位名称查找",httpMethod = "GET")
    @GetMapping("/getByPostName")
    @ResponseBody
    public ResEntity getByPostName (@RequestParam("postname") String postname) {
        return new ResEntity(sysPostService.getByPostName(postname));
    }

    @ApiOperation(value = "按岗位菜单",httpMethod = "GET")
    @GetMapping("/getPostName")
    @ResponseBody
    public ResEntity postList()
    {
        return new ResEntity(sysPostService.queryPostList());
    }


}
