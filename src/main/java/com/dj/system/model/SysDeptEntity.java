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
 * @date 2020-03-19 上午9:40
 * @company www.dfdk.com.cn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_dept")
public class SysDeptEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2680711348103859318L;
    @TableId(value = "dept_id",type = IdType.AUTO)
    private Integer deptId;

    @TableField(value = "parent_id")
    private Integer pid;

    @TableField(value = "dept_name")
    private String deptName;

    @TableField(value = "order_num")
    private String orderNum;

    @TableField(value = "leader")
    private String leader;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "email")
    private String email;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "del_flag")
    private Integer delFlag;
}
