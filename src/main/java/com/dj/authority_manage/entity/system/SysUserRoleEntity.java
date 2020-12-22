package com.dj.authority_manage.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dj
 * @date 2020-12-22 14:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_user_role")
public class SysUserRoleEntity {
    private static final long serialVersionUID = -2497087732770760213L;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "role_id")
    private Long roleId;
}
