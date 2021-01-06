package com.dj.system.controller;

import com.dj.annotation.SysLog;
import com.dj.common.QuerySysDeptRequest;
import com.dj.common.ResEntity;
import com.dj.system.model.SysDeptEntity;
import com.dj.system.service.SysDeptService;
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
 * @author jcj
 * @date 2020-03-20 上午8：53
 * @company www.dfdk.com.cn
 */
@Api(tags = "系统部门API")
@Controller
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @SysLog(title = "添加部门信息")
    @ApiOperation(value = "添加部门信息",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "部门实体类",name = "SysDeptEntity",required = true),
            @ApiImplicitParam(value = "1:代表添加;2:代表修改",name = "type",required = true)
    })
    @PostMapping("/addOrUpdateDept")
    @ResponseBody
    public ResEntity addOrUpdateDept (SysDeptEntity sysDeptEntity, int type) {
        if (type == 1) {
                return sysDeptService.saveDept(sysDeptEntity);
        } else {
            return sysDeptService.updateDept(sysDeptEntity);
        }
    }

    @ApiOperation(value = "获取所有的分页查询",httpMethod = "GET")
    @GetMapping("/listDept")
    @ResponseBody
    public ResEntity<?> querySysDeptPage (@Validated QuerySysDeptRequest request) {
        return sysDeptService.querySysDeptPage(request);
    }

    @RequestMapping("/delSysDept")
    @ResponseBody
    public ResEntity<?> delSysDept(SysDeptEntity sysDeptEntity){
        return sysDeptService.removeByDeptId(sysDeptEntity.getDeptId());
    }

    /**
     *
     * 查询部门（树形结构）
     *
     * @return
     */
    @ApiOperation(value = "获取部门菜单",httpMethod = "GET")
    @GetMapping("/getDept")
    @ResponseBody
    public ResEntity deptTreeList()
          {
        return  new ResEntity(sysDeptService.querydeptList());
    }

    /**
     * 查询部门（列表结构）
     *
     * @return
     */
    @GetMapping("/listSysDept")
    @ResponseBody
    public ResEntity deptList(){
        return sysDeptService.sysDeptList();
    }

    /**
     * 根据部门id获取部门名称
     * @param deptId
     * @return
     */
    @GetMapping("/getDeptName")
    @ResponseBody
    public ResEntity selectDeptNameById(String deptId){
        return new ResEntity(sysDeptService.selectDeptNameById(deptId));
    }

}
