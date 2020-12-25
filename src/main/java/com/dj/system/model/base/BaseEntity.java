package com.dj.system.model.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wxl
 * @date 2020-03-17 上午8:53
 * @company www.dfdk.com.cn
 */
@Getter
@Setter
public class BaseEntity {

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    protected String createBy;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected String createTime;

    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    protected String updateBy;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected String updateTime;

    @TableField(value = "remark")
    protected String remark;

}
