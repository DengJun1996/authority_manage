package com.dj.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jetlag
 * @date 2020/6/23
 **/
@Data
@ApiModel
@AllArgsConstructor
public class SysRoleRequest {

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单id数组")
    private Long[] menuIds;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "roleName不能为空")
    private String roleName;

    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    @NotBlank(message = "roleKey不能为空")
    private String roleKey;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否软删除 0 正常 2 删除")
    private int status;

}
