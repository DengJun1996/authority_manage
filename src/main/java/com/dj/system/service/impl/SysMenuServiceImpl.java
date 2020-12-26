package com.dj.system.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.system.mapper.SysMenuMapper;
import com.dj.system.mapper.SysRoleMenuMapper;
import com.dj.system.mapper.SysUserMapper;
import com.dj.system.model.SysMenuEntity;
import com.dj.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxl
 * @date 2020-03-17 上午9:31
 * @company www.dfdk.com.cn
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
}
