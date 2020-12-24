package com.dj.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author jetlag
 * @date 2020/5/9
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户登录数据模型")
public class SysUserVo {

    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, min = 1, message = "用户名不符合规则")
    @ApiModelProperty(value = "用户名", name = "username")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(max = 16, min = 6, message = "密码不符合规则")
    @ApiModelProperty(value = "密码", name = "password")
    private String password;
}
