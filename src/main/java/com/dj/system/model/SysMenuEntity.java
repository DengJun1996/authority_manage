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
 * @date 2020-03-17 上午9:14
 * @company www.dfdk.com.cn
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_menu")
public class SysMenuEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7716763362378042981L;

    @TableId(value = "menu_id",type = IdType.AUTO)
    private Long menuId;

    @TableField(value = "menu_name")
    private String menuName;

    @TableField(value = "parent_id")
    private String pid;

    @TableField(value = "order_num")
    private String orderNum;

    @TableField(value = "url")
    private String url;

    @TableField(value = "menu_type")
    private String menuType;

    @TableField(value = "visible")
    private String visible;

    @TableField(value = "perms")
    private String perms;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "update_time")
    private String updateTime;

    @TableField(value = "update_by")
    private String updateBy;

}
