package com.dj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.common.ResEntity;
import com.dj.common.ResultEnum;
import com.dj.config.JWTHelper;
import com.dj.config.ConfigProperties;
import com.dj.constants.RedisConstant;
import com.dj.system.controller.BaseController;
import com.dj.system.model.SysRoleEntity;
import com.dj.system.model.SysUserRoleEntity;
import com.dj.system.service.SysRoleService;
import com.dj.system.utils.ShiroUtils;
import com.dj.common.SysUserStatusEnum;
import com.dj.system.mapper.SysUserMapper;
import com.dj.system.mapper.SysUserRoleMapper;
import com.dj.system.model.SysUserEntity;

import com.dj.system.service.SysUserService;
import com.dj.system.vo.SysUserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wxl
 * @date 2020-03-17 上午9:32
 * @company www.dfdk.com.cn
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {
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
    private BaseController baseController;
    @Autowired
    private SysRoleService sysRoleService;


    @Override
    public SysUserEntity getByUserName(String loginName) {
        QueryWrapper<SysUserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.lambda().eq(SysUserEntity::getLoginName, loginName);
        return this.baseMapper.selectOne(userEntityQueryWrapper);
    }

    /**
     * 登陆
     *
     * @param sysUserVo
     * @return
     */
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

        String token = jwtHelper.generateToken(userEntity);
        // 设置用户Token
        redissonClient.getBucket(RedisConstant.PREFIX_USER_TOKEN + DigestUtils.md5Hex(token))
                .set(token, configProperties.getJwt().getRedisExpiration(), TimeUnit.MINUTES);
        return ResEntity.SUCCESS(ResultEnum.SUCCESS.getCode(), configProperties.getJwt().getPrefix() + token);
    }

    /**
     * 根据用户id获取角色
     *
     * @param userId
     * @return
     */
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

    /**
     * 登出
     *
     * @param header
     * @return
     */
    @Override
    public ResEntity<?> tokenLogout(String header) {
        String perfix = StringUtils.remove(header, configProperties.getJwt().getPrefix());
        String md5HexToken = DigestUtils.md5Hex(perfix);
        //删除token
        redissonClient.getBucket(RedisConstant.PREFIX_USER_TOKEN + md5HexToken).delete();
        //删除权限缓存
        redissonClient.getBucket(RedisConstant.getPrefixUserPermissions(jwtHelper.getDnUserId(perfix).toString())).delete();
        ShiroUtils.logout();
        return ResEntity.SUCCESS();
    }
}
