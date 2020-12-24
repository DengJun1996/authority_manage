package com.dj.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dj.system.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wxl
 * @date 2020-03-17 上午8:46
 * @company www.dfdk.com.cn
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class SysUserEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2121002539102809973L;
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @TableField(value = "dept_id")
    private Long deptId;

    @TableField(value = "login_name")
    private String loginName;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "user_type")
    private String userType;

    @TableField(value = "email")
    private String email;

    @TableField(value = "salt")
    private String salt;

    @TableField(value = "phone_number")
    private String phoneNumber;

    @TableField(value = "sex")
    private String sex;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "password")
    private String password;

    @TableField(value = "status")
    private int status;

    @TableField(value = "del_flag")
    private int delFlag;

    @TableField(value = "role_id")
    private Long roleId;

    @TableField(value = "post_id")
    private Long postId;

    @TableField(exist = false)
    private String deptName;

    @TableField(exist = false)
    private String roleName;

    @TableField(exist = false)
    private String postName;

}
