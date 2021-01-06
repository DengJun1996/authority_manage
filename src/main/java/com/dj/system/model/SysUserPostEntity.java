package com.dj.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wxl
 * @date 2020-03-19 上午9:54
 * @company www.dfdk.com.cn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user_post")
public class SysUserPostEntity implements Serializable {

    private static final long serialVersionUID = -7548405497462499066L;
    @TableField(value = "user_id")
    private Long userId;
    @TableField(value = "post_id")
    private Long postId;

}
