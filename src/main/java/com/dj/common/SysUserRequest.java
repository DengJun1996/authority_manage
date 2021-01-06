package com.dj.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author jetlag
 * @date 2020/7/1
 **/
@Data
@NoArgsConstructor
public class SysUserRequest {

    private Long userId;

    @NotBlank(message = "登录名不能为空")
    @Length(min = 3, max = 10, message = "登陆名不符合规则 长度3到10")
    private String loginName;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 2, max = 10, message = "用户名参数不符合规则 长度2到10")
    private String userName;

    @NotBlank(message = "用户类型不能为空")
    private String userType;

    private String email;

    private String phoneNumber;

    @NotBlank(message = "性别不能为空")
    private String sex;

    @NotNull(message = "用户状态不能为空")
    private Integer status;

    private String avatar;

    @NotNull(message = "部门不能为空")
    private Long deptId;

    @NotNull(message = "岗位不能为空")
    private Long postId;

    @NotNull(message = "角色不能为空")
    private Long roleId;

    private String type;

}
