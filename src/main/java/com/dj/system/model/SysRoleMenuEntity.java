package com.dj.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wxl
 * @date 2020-03-19 上午10:00
 * @company www.dfdk.com.cn
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_role_menu")
public class SysRoleMenuEntity implements Serializable {

    private static final long serialVersionUID = -4408968084676453575L;

    @TableField(value = "role_id")
    private Long roleId;

    @TableField(value = "menu_id")
    private Long menuId;

    @TableField(exist = false)
    private Long pId;

    public SysRoleMenuEntity(Long roleId, Long menuId){
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
