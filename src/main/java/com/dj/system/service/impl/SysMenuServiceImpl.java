package com.dj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.common.MenuTypeEnum;
import com.dj.common.QuerySysMenuRequest;
import com.dj.common.ResEntity;
import com.dj.system.mapper.SysMenuMapper;
import com.dj.system.mapper.SysRoleMenuMapper;
import com.dj.system.mapper.SysUserMapper;
import com.dj.system.model.SysMenuEntity;
import com.dj.system.model.SysRoleMenuEntity;
import com.dj.system.model.SysUserEntity;
import com.dj.system.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    @Override
    public ResEntity<?> removeByMenuId(Long menuId){
        //判断菜单下面是否有子菜单
        Integer count = this.baseMapper.selectCount(new LambdaQueryWrapper<SysMenuEntity>().eq(SysMenuEntity::getPid, menuId));
        if (count > 0){
            return ResEntity.ERROR("存在子菜单，不能删除");
        }
        //删除角色菜单中间表的相关信息
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenuEntity>().eq(SysRoleMenuEntity::getMenuId,menuId));
        this.baseMapper.deleteById(menuId);
        return ResEntity.SUCCESS("删除成功");
    }


    /**
     * 根据菜单名称查询菜单
     *
     * @param menuName 菜单名称
     * @return
     */
    @Override
    public SysMenuEntity getByMenuName(String menuName) {
        QueryWrapper<SysMenuEntity> menuEntityQueryWrapper = new QueryWrapper<>();
        menuEntityQueryWrapper.lambda().eq(StringUtils.isNotBlank(menuName), SysMenuEntity::getMenuName, menuName);

        return this.baseMapper.selectOne(menuEntityQueryWrapper);
    }

    /**
     * 根据菜单类型查询对应菜单
     *
     * @param menuTypeEnum
     * @return
     */
    @Override
    public ResEntity<?> getSupMenuTree(MenuTypeEnum menuTypeEnum) {
        if (menuTypeEnum == MenuTypeEnum.M) {
            return ResEntity.SUCCESS();
        }
        if (menuTypeEnum == MenuTypeEnum.C) {
            LambdaQueryWrapper<SysMenuEntity> menuEntityQueryWrapper = new LambdaQueryWrapper<>();
            menuEntityQueryWrapper.eq(SysMenuEntity::getMenuType, MenuTypeEnum.M.name());
            List<SysMenuEntity> sysMenuEntities = this.baseMapper.selectList(menuEntityQueryWrapper);
            return ResEntity.SUCCESS(sysMenuEntities);
        }
        if (menuTypeEnum == MenuTypeEnum.F){
            LambdaQueryWrapper<SysMenuEntity> menuEntityQueryWrapper = new LambdaQueryWrapper<>();
            menuEntityQueryWrapper.eq(SysMenuEntity::getMenuType, MenuTypeEnum.C.name());
            List<SysMenuEntity> sysMenuEntities = this.baseMapper.selectList(menuEntityQueryWrapper);
            return ResEntity.SUCCESS(sysMenuEntities);
        }
        return ResEntity.ERROR("菜单类型错误");
    }

    /**
     * 分页查询菜单数据
     *
     * @param request
     * @return
     */
    @Override
    public ResEntity<?> querySysMenuPage(QuerySysMenuRequest request) {
        IPage<SysMenuEntity> page = new Page<>(request.getCurrentPage(), request.getPageSize());
        LambdaQueryWrapper<SysMenuEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(org.apache.commons.lang3.StringUtils.isNotBlank(request.getMenuName()),
                SysMenuEntity::getMenuName, request.getMenuName());

        IPage<SysMenuEntity> selectPage = this.baseMapper.selectPage(page, lambdaQueryWrapper);
        return ResEntity.OK(selectPage.getRecords(), Integer.parseInt(selectPage.getTotal() + ""));
    }

    /**
     * 修改或编辑菜单
     *
     * @param sysMenuEntity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResEntity<?> saveOrUpdateMenu(SysMenuEntity sysMenuEntity) {
        //如果菜单类型是菜单或者按钮，父级菜单不能为空
        if (!"M".equals(sysMenuEntity.getMenuType()) && ("".equals(sysMenuEntity.getPid()) || "0".equals(sysMenuEntity.getPid()))){
            return ResEntity.ERROR("请选择上级菜单");
        }
        //执行新增操作
        if (sysMenuEntity.getMenuId() == null){
            if (!"M".equals(sysMenuEntity.getMenuType()) && "".equals(sysMenuEntity.getPid())){
                return ResEntity.ERROR("请选择上级菜单");
            }
            //执行新增操作
            if ("".equals(sysMenuEntity.getPid()) && "M".equals(sysMenuEntity.getMenuType())){
                sysMenuEntity.setPid("0");
            }
            int sysMenuCount = this.baseMapper.selectCount(new LambdaQueryWrapper<SysMenuEntity>().eq(SysMenuEntity::getPerms,sysMenuEntity.getPerms()).eq(SysMenuEntity::getMenuName,sysMenuEntity.getMenuName()));
            if (sysMenuCount >= 1) {
                return ResEntity.ERROR("权限标识或菜单名已存在");
            }
            this.baseMapper.insert(sysMenuEntity);
            //将新增的菜单赋给超级管理员
            LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();
            //查询出超级管理员
            queryWrapper.eq(SysUserEntity::getUserType,"1");
            List<SysUserEntity> userList = sysUserMapper.selectList(queryWrapper);
            if (userList.size() > 0) {
                SysUserEntity userEntity = userList.get(0);
                //查询出刚添加的菜单
                SysMenuEntity menu = baseMapper.selectOne(new LambdaQueryWrapper<SysMenuEntity>().eq(SysMenuEntity::getPerms, sysMenuEntity.getPerms()));
                //新增角色菜单中间表
                sysRoleMenuMapper.insert(new SysRoleMenuEntity(userEntity.getRoleId(),menu.getMenuId()));
            }
            return ResEntity.SUCCESS("新增菜单成功");
        }
        //执行修改操作
        this.baseMapper.updateById(sysMenuEntity);
        return ResEntity.SUCCESS("修改菜单成功");
    }

}
