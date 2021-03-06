package com.dj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.common.*;

import com.dj.config.ConfigProperties;
import com.dj.config.JWTHelper;
import com.dj.constants.RedisConstant;
import com.dj.system.controller.BaseController;
import com.dj.system.mapper.SysUserMapper;
import com.dj.system.mapper.SysUserPostMapper;
import com.dj.system.mapper.SysUserRoleMapper;
import com.dj.system.model.SysRoleEntity;
import com.dj.system.model.SysUserEntity;
import com.dj.system.model.SysUserPostEntity;
import com.dj.system.model.SysUserRoleEntity;
import com.dj.system.service.SysRoleService;
import com.dj.system.service.SysUserService;
import com.dj.system.utils.ShiroUtils;
import com.dj.system.vo.ResVo;
import com.dj.system.vo.SysUserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wxl
 * @date 2020-03-17 上午9:32
 * @company www.dfdk.com.cn
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    private static Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired
    private SysUserPostMapper sysUserPostMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired(required = false)
    private SysUserService sysUserService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private JWTHelper jwtHelper;
    @Autowired
    private ConfigProperties configProperties;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private BaseController baseController;

    /***静态变量，用来记录当前在线连接数。应该把它设计成线程安全的*/
    private static final AtomicInteger ON_LINE_COUNT = new AtomicInteger(0);


    @Override
    public SysUserEntity getByUserName(String loginName) {
        QueryWrapper<SysUserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.lambda().eq(SysUserEntity::getLoginName, loginName);
        return this.baseMapper.selectOne(userEntityQueryWrapper);
    }

    @Override
    public ResVo<SysUserEntity> page(SysUserEntity userEntity, Long currentPage, Long countPage) {
        IPage<SysUserEntity> iPage = new Page<>(currentPage, countPage);
        QueryWrapper<SysUserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.lambda()
                .eq(userEntity.getDeptId() != null, SysUserEntity::getDeptId, userEntity.getDeptId())
                .like(StringUtils.isNotEmpty(userEntity.getLoginName()), SysUserEntity::getLoginName, userEntity.getLoginName())
                .like(StringUtils.isNotEmpty(userEntity.getPhoneNumber()), SysUserEntity::getPhoneNumber, userEntity.getPhoneNumber());
        return new ResVo<>(this.baseMapper.selectPage(iPage, userEntityQueryWrapper));
    }

    @Override
    public ResEntity<Boolean> deleteSysUser(SysUserEntity sysUserEntity) {
        Long userId = sysUserEntity.getUserId();
        //获取该用户类型
        String sysUserType = baseController.getSysUserType();
        Long sysUserId = baseController.getSysUserId();
        if (sysUserId.equals(userId)){
            return new ResEntity("自己不能删除自己");
        }
        //如果是超级管理员，删除用户
        if( sysUserType.equals("1")){
            sysUserPostMapper.delete(new QueryWrapper<SysUserPostEntity>().lambda().eq(SysUserPostEntity::getUserId, userId));
            sysUserRoleMapper.delete(new QueryWrapper<SysUserRoleEntity>().lambda().eq(SysUserRoleEntity::getUserId, userId));
            return new ResEntity(sysUserService.removeById(userId));
        }
        SysUserEntity sysUser = new SysUserEntity();
        //delfalg的标志为2时，表示删除
        sysUser.setDelFlag(2);
        return new ResEntity(this.baseMapper.update(sysUser,new QueryWrapper<SysUserEntity>().eq("user_id",userId)));

    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public ResEntity<Boolean> saveOrUpdateSysUser(SysUserRequest request) {
        SysUserPostEntity sysUserPostEntity = new SysUserPostEntity();
        sysUserPostEntity.setPostId(request.getPostId());
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setRoleId(request.getRoleId());

        SysUserEntity sysUserEntity = new SysUserEntity();

        sysUserEntity.setRoleId(request.getRoleId());
        sysUserEntity.setPostId(request.getPostId());
        sysUserEntity.setDeptId(request.getDeptId());
        sysUserEntity.setEmail(request.getEmail());
        sysUserEntity.setSex(request.getSex());
        sysUserEntity.setPhoneNumber(request.getPhoneNumber());
        sysUserEntity.setUserType(request.getUserType());
        sysUserEntity.setAvatar(request.getAvatar());
        sysUserEntity.setStatus(request.getStatus());

        if (request.getUserId() == null) {
            SysUserEntity sysUserByLoginName = sysUserService.getSysUserByLoginName(request.getLoginName());
            if (sysUserByLoginName != null) {
                return ResEntity.ERROR("该登录名已存在");
            }
            sysUserEntity.setLoginName(request.getLoginName());
            String salt = UUID.randomUUID().toString();
            sysUserEntity.setPassword(ShiroUtils.sha256("000000", salt));
            sysUserEntity.setSalt(salt);
            sysUserEntity.setUserName(request.getUserName());
            int insert = this.baseMapper.insert(sysUserEntity);
            if (insert > 0) {
                sysUserPostEntity.setUserId(sysUserEntity.getUserId());
                sysUserRoleEntity.setUserId(sysUserEntity.getUserId());
                sysUserPostMapper.insert(sysUserPostEntity);
                sysUserRoleMapper.insert(sysUserRoleEntity);
                return ResEntity.SUCCESS(Boolean.TRUE);
            }
            return ResEntity.SUCCESS(Boolean.FALSE);
        } else {
            sysUserEntity.setUserId(request.getUserId());
            sysUserEntity.setUserName(request.getUserName());
            sysUserEntity.setLoginName(request.getLoginName());
            sysUserPostEntity.setUserId(request.getUserId());
            sysUserRoleEntity.setUserId(request.getUserId());
            sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRoleEntity>().eq(SysUserRoleEntity::getUserId, request.getUserId()));
            sysUserPostMapper.delete(new LambdaQueryWrapper<SysUserPostEntity>().eq(SysUserPostEntity::getUserId, request.getUserId()));
            sysUserPostMapper.insert(sysUserPostEntity);
            sysUserRoleMapper.insert(sysUserRoleEntity);
            sysUserService.saveOrUpdate(sysUserEntity);
            return ResEntity.SUCCESS(Boolean.TRUE);
        }
    }


    @Override
    public boolean updatePwd(SysUserEntity userEntity) {
        return this.updateById(userEntity);
    }

    @Override
    public ResEntity<?> querySysUserPage(QuerySysUserRequest request) {
        request.setStartPage(request.getCurrentPage());
        // TODO 暂定数据
        // 校验是否为超级管理员
        //调用方法获取用户类型 用户类型为1，表示为超级管理员
        String userType = baseController.getSysUserType();
        //不为超级管理员时，只能查询未软删除的用户,不能给他查询超级管理员
        if (!userType.equals("1")){
            int count = this.baseMapper.selectUserCount(request);
            List<SysUserEntity> sysUserEntities = this.baseMapper.queryUserPage(request);
            return ResEntity.OK(sysUserEntities,count);
        }else{
            //为超级管理员时，可以查询出软删除的用户
            int count = this.baseMapper.selectAdminUserCount(request);
            List<SysUserEntity> sysUserEntities = this.baseMapper.queryAdminUserList(request);
            return ResEntity.OK(sysUserEntities,count);
        }
    }

    @Override
    public ResEntity<?> tokenLogin(SysUserVo sysUserVo) {
        String password = sysUserVo.getPassword();
        String loginName = sysUserVo.getUsername();

        SysUserEntity userEntity = getByUserName(loginName);

        if (userEntity == null) {
            return ResEntity.ERROR(ResultEnum.UNKNOWN_ACCOUNT);
        }

        // 校验用户是否有效 该用户已注销
        if (userEntity.getDelFlag() == SysUserStatusEnum.DEL_FLAG1.getCode()) {
            return ResEntity.ERROR(ResultEnum.LOCKED_ACCOUNT);
        }
        // 该用户已停用
        if (userEntity.getStatus() == SysUserStatusEnum.USER_FREEZE.getCode()) {
            return ResEntity.ERROR(ResultEnum.LOCKED_ACCOUNT);
        }


        String checkPassword = ShiroUtils.sha256(password, userEntity.getSalt());
        // 校验用户密码
        if (!checkPassword.equalsIgnoreCase(userEntity.getPassword())) {
            return ResEntity.ERROR(ResultEnum.INCORRECT_CREDENTIALS);
        }

        int count = ON_LINE_COUNT.incrementAndGet();
        log.info("有新的连接加进来,当前的连接数为{}", count);

        String token = jwtHelper.generateToken(userEntity);
        // 设置用户Token
        redissonClient.getBucket(RedisConstant.PREFIX_USER_TOKEN + DigestUtils.md5Hex(token))
                .set(token, configProperties.getJwt().getRedisExpiration(), TimeUnit.MINUTES);
        return ResEntity.SUCCESS(ResultEnum.SUCCESS.getCode(), configProperties.getJwt().getPrefix() + token);
    }

    @Override
    public ResEntity<?> tokenLogout(String token) {
        String removePrefix = StringUtils.remove(token, configProperties.getJwt().getPrefix());
        String md5HexToken = DigestUtils.md5Hex(removePrefix);
        // 刪除token
        redissonClient.getBucket(RedisConstant.PREFIX_USER_TOKEN + md5HexToken).delete();
        // 刪除权限缓存
        redissonClient.getBucket(RedisConstant.getPrefixUserPermissions((jwtHelper.getDnUserId(removePrefix).toString()))).delete();
        ShiroUtils.logout();

        int count = ON_LINE_COUNT.decrementAndGet();
        log.info("有一连接关闭！当前在线人数为" + count);

        return ResEntity.SUCCESS();
    }

    @Override
    public List<SysRoleEntity> getUserRoles(Long userId) {
        QueryWrapper<SysUserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(SysUserRoleEntity::getRoleId).eq(SysUserRoleEntity::getUserId, userId);
        List<Object> userRoles = sysUserRoleMapper.selectObjs(queryWrapper);
        if (userRoles.isEmpty()) {
            return new ArrayList<>();
        }
        QueryWrapper<SysRoleEntity> roleEntityQueryWrapper = new QueryWrapper<>();
        roleEntityQueryWrapper.lambda().in(SysRoleEntity::getRoleId, userRoles);
        return sysRoleService.list(roleEntityQueryWrapper);
    }

    @Override
    public SysUserEntity getSysUserByLoginName(String loginName) {
        LambdaQueryWrapper<SysUserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUserEntity::getLoginName, loginName);
        return sysUserService.getOne(lambdaQueryWrapper);
    }
}
