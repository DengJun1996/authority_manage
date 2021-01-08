package com.dj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.system.mapper.SysMenuMapper;
import com.dj.system.mapper.SysRoleMenuMapper;
import com.dj.system.model.SysMenuEntity;
import com.dj.system.model.SysRoleMenuEntity;
import com.dj.system.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wxl
 * @date 2020-03-19 上午10:53
 * @company www.dfdk.com.cn
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuEntity> implements SysRoleMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<Long> listMenuIds(Long roleId) {
        QueryWrapper<SysRoleMenuEntity> sysRoleMenuEntityQueryWrapper = new QueryWrapper<>();
        sysRoleMenuEntityQueryWrapper
                .lambda()
                .eq(SysRoleMenuEntity::getRoleId, roleId);
        return this.baseMapper.selectList(sysRoleMenuEntityQueryWrapper).stream().map(m -> m.getMenuId()).collect(Collectors.toList());
    }

    @Override
    public boolean removeByMenuId(List<Long> menuIds) {
        QueryWrapper<SysRoleMenuEntity> sysRoleMenuEntityQueryWrapper = new QueryWrapper<>();
        sysRoleMenuEntityQueryWrapper
                .lambda()
                .in(SysRoleMenuEntity::getMenuId, menuIds);
        int row = this.baseMapper.delete(sysRoleMenuEntityQueryWrapper);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean removeByRoleId(List<Long> roleId) {
        LambdaQueryWrapper<SysRoleMenuEntity> sysRoleMenuEntityQueryWrapper = new LambdaQueryWrapper<>();
        sysRoleMenuEntityQueryWrapper.in(SysRoleMenuEntity::getRoleId, roleId);
        int row = this.baseMapper.delete(sysRoleMenuEntityQueryWrapper);
        if (row > 0) {
            return true;
        }
        return false;
    }

    public List<Object> excuteMenu(List<SysMenuEntity> listSysMenuEntity) {
        List<Object> listAllMenu = new ArrayList<>();
        for (SysMenuEntity sysMenuEntity : listSysMenuEntity) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", sysMenuEntity.getMenuId());
            map.put("title", sysMenuEntity.getMenuName());

            //如果菜单类型为按钮
            if (sysMenuEntity.getMenuType().equals("F")) {
                listAllMenu.add(map);
            } else {//菜单类型为目录或菜单，递归深入
                QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda()
                        .eq(SysMenuEntity::getPid, sysMenuEntity.getMenuId());
                List<SysMenuEntity> list = sysMenuMapper.selectList(queryWrapper);
                map.put("children", excuteMenu(list));
                listAllMenu.add(map);
            }
        }
        return listAllMenu;
    }

    /**
     * @return
     * @author dj
     * @date 2020-06-22 11:44
     */
    @Override
    public List<Object> listAllMenu() {
        QueryWrapper<SysMenuEntity> queryWrapperM = new QueryWrapper<>();
        queryWrapperM.lambda()
                .eq(SysMenuEntity::getMenuType, "M");
        List<SysMenuEntity> listM = sysMenuMapper.selectList(queryWrapperM);
        return excuteMenu(listM);
    }

    @Override
    public Map<String, Object> listMenuByRoleID(Long roleId) {
        Map<String, Object> map = new HashMap<>();
        List<Long> list = new ArrayList<>();
        //所有的菜单结构
        map.put("listAllMenu", listAllMenu());
        //根据角色id查询该角色拥有的菜单
        List<SysRoleMenuEntity> roleMenuEntityList = this.baseMapper.selectarrayMenuId(roleId);
        //先将该角色拥有的菜单的菜单id存入list中，后面会去除部分
        for (SysRoleMenuEntity roleMenu: roleMenuEntityList
             ) {
            list.add(roleMenu.getMenuId());
        }

        map.put("listMySelfMenu",list.toArray());
        //遍历菜单，去除掉list中有子菜单的菜单id
        for (SysRoleMenuEntity roleMenu: roleMenuEntityList
        ) {
            //如果某个菜单下面有子菜单，则这个菜单的id不需要放入list集合中，只需把子菜单传进去，这是因为前段框架有bug，只能这样传
            if (list.contains(roleMenu.getPId())){
                list.remove(roleMenu.getPId());
            };
        }
        map.put("arrayMenuId", list.toArray());
        return map;
    }


    /*@Override
    public List<Object> listMenu() {

        //查询出所有目录
        QueryWrapper<SysMenuEntity> queryWrapperM = new QueryWrapper<>();
        queryWrapperM.lambda()
                .eq(SysMenuEntity::getMenuType,"M");
        List<SysMenuEntity> listM = sysMenuMapper.selectList(queryWrapperM);
        List<Object> listm = new ArrayList<>();
        for (SysMenuEntity menuM :listM
        ) {
            HashMap<String, Object> mapM = new HashMap<>();
            mapM.put("id",menuM.getMenuId());
            mapM.put("title",menuM.getMenuName());
           //查询出每个目录下所有的菜单
            QueryWrapper<SysMenuEntity> queryWrapperC = new QueryWrapper<>();
            queryWrapperC.lambda()
                    .eq(SysMenuEntity::getPid,menuM.getMenuId())
                    .eq(SysMenuEntity::getMenuType,"C");
            List<SysMenuEntity> listC = sysMenuMapper.selectList(queryWrapperC);
            List<Object> listc = new ArrayList<>();
            for (SysMenuEntity menuC:listC
                 ) {
                HashMap<Object, Object> mapC = new HashMap<>();
                mapC.put("id", menuC.getMenuId());
                mapC.put("title",menuC.getMenuName());
                //查询出每个菜单下所有按钮
                QueryWrapper<SysMenuEntity> queryWrapperF= new QueryWrapper<>();
                queryWrapperF.lambda()
                        .eq(SysMenuEntity::getPid,menuC.getMenuId())
                        .eq(SysMenuEntity::getMenuType,"F");
                List<SysMenuEntity> listF = sysMenuMapper.selectList(queryWrapperF);
                List<Object> listf = new ArrayList<>();
                for (SysMenuEntity menuF:listF
                     ) {
                    HashMap<String, Object> mapF = new HashMap<>();
                    mapF.put("id",menuF.getMenuId());
                    mapF.put("title",menuF.getMenuName());
                    listf.add(mapF);
                }
                mapC.put("children",listf);
                listc.add(mapC);
            }
            mapM.put("children",listc);
           listm.add(mapM);
        }

        //查询出每个菜单下所有按钮
        return listm;
    }*/
}
