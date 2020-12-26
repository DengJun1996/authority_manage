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
 * @date 2020-03-17 上午9:04
 * @company www.dfdk.com.cn
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_role")
public class SysRoleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2891689831528358598L;

    @TableId(value = "role_id",type = IdType.AUTO)
    private Long roleId;

    @TableField(value = "role_name")
    private String roleName;

    @TableField(value = "role_key")
    private String roleKey;

    @TableField(value = "role_sort")
    private int roleSort;

    @TableField(value = "status")
    private int status;

    @TableField(value = "del_flag")
    private int delFlag;

}
