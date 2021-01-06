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
 * @date 2020-03-19 上午9:50
 * @company www.dfdk.com.cn
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_post")
public class SysPostEntity extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -4535313965972789871L;
    @TableId(value = "post_id",type = IdType.AUTO)
    private Long postId;

    @TableField(value = "post_code")
    private String postCode;

    @TableField(value = "post_name")
    private String postName;

    @TableField(value = "post_sort")
    private int postSort;

    @TableField(value = "status")
    private int status;

}
