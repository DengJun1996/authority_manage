package com.dj.system.realm;

import com.dj.config.JWTHelper;
import com.dj.config.JwtToken;
import com.dj.config.ConfigProperties;
import com.dj.constants.RedisConstant;
import com.dj.common.SysUserStatusEnum;
import com.dj.system.model.SysUserEntity;
import com.dj.system.service.SysUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author wxl
 * @date 2020-03-20 下午12:49
 * @company www.dfdk.com.cn
 */
public class DKRealm  extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(AuthorizingRealm.class);

    @Autowired
    private SysUserService sysUserService;


    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private ConfigProperties configProperties;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     * @author wxl
     * @date 2020-03-20
     * @param principalCollection
     * @return
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
       /* SysUserEntity userEntity = (SysUserEntity) principalCollection.getPrimaryPrincipal();
        String loginName = userEntity.getLoginName();
        List<String> permList = new ArrayList<>();
        if (loginName.equalsIgnoreCase(Constant.SUPER_ADMIN)) {
            LambdaQueryWrapper<SysMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.select(SysMenuEntity::getPerms);
            List<String> adminPermissions = sysMenuService.listObjs(queryWrapper, Objects::toString);
            permList.addAll(adminPermissions);
        } else {
            // 根据用户ID查找所有的权限
            List<SysRoleEntity> userRoles = sysUserService.getUserRoles(userEntity.getUserId());
            if (!userRoles.isEmpty()) {
                List<Long> roles = userRoles.stream().map(SysRoleEntity::getRoleId).collect(Collectors.toList());
                if (!roles.isEmpty()) {
                    QueryWrapper<SysRoleMenuEntity> roleMenuEntityQueryWrapper = new QueryWrapper<>();
                    roleMenuEntityQueryWrapper.lambda().in(SysRoleMenuEntity::getRoleId, roles);
                    List<SysRoleMenuEntity> sysRoleMenuEntities = sysRoleMenuMapper.selectList(roleMenuEntityQueryWrapper);
                    List<Long> menuIds = sysRoleMenuEntities.stream().map(SysRoleMenuEntity::getMenuId).collect(Collectors.toList());
                    LambdaQueryWrapper<SysMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.select(SysMenuEntity::getPerms).in(SysMenuEntity::getMenuId, menuIds);
                    List<String> adminPermissions = sysMenuService.listObjs(queryWrapper, Objects::toString);
                    permList.addAll(adminPermissions);
                }
            }
        }
        Set<String> permSet = new HashSet<>();
        permList.stream()
                .filter(StringUtils::isNotEmpty)
                .forEach(p -> permSet.addAll(Arrays.asList(p.trim().split(","))));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permSet);
        return info;*/
    }

    /**
     * 用户信息认证是在用户进行登录的时候进行验证(不存redis)
     * 也就是说验证用户输入的账号和密码是否正确，错误抛出异常
     *
     * @param authenticationToken 用户登录的账号密码信息
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        SysUserEntity entity = sysUserService.getByUserName(token.getUsername());
        String token = (String) authenticationToken.getCredentials();
        if (token == null) {
            log.info("————————身份认证失败——————————");
            throw new AuthenticationException("TOKEN IS ENPTY!");
        }

//        if (entity == null) {
//            throw new UnknownAccountException("账号不正确");
//        }
//
//        if (entity.getStatus() == 1) {
//            throw new LockedAccountException("账号已被锁定,请联系管理员");
//        } else if (entity.getStatus() == 2) {
//            throw new LockedAccountException("账号已被停用,请联系管理员");
//        }
        SysUserEntity entity = checkUserTokenIsEffect(StringUtils.remove(token, configProperties.getJwt().getPrefix()));
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(entity, token, getName());
        return info;
    }

//    @Override
//    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
//        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
//        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
//        super.setCredentialsMatcher(shaCredentialsMatcher);
//    }

    /**
     * 校验token的有效性
     *
     * @param token
     */
    public SysUserEntity checkUserTokenIsEffect(String token) throws AuthenticationException {
        // 解密获得username，用于和数据库进行对比
        String username = jwtHelper.getLoginName(token);
        if (username == null) {
            throw new AuthenticationException("token非法无效!");
        }

        // 查询用户信息
        log.debug("———校验token是否有效————checkUserTokenIsEffect——————— "+ token);
        SysUserEntity sysUserEntity = sysUserService.getByUserName(username);

        if (sysUserEntity.getDelFlag() == SysUserStatusEnum.DEL_FLAG1.getCode()) {
            throw new LockedAccountException("账号已被注销,请联系管理员");
        } else if (sysUserEntity.getStatus() == SysUserStatusEnum.USER_FREEZE.getCode()) {
            throw new LockedAccountException("账号已被停用,请联系管理员");
        }

        // 校验token是否超时失效 & 或者账号密码是否错误
        if (!jwtTokenRefresh(token, sysUserEntity)) {
            throw new AuthenticationException("Token失效，请重新登录!");
        }
        return sysUserEntity;
    }

    /**
     * JWTToken刷新生命周期 （实现： 用户在线操作不掉线功能）
     * 1、登录成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面(这时候k、v值一样)，缓存有效期设置为Jwt有效时间的2倍
     * 2、当该用户再次请求时，通过JWTFilter层层校验之后会进入到doGetAuthenticationInfo进行身份验证
     * 3、当该用户这次请求jwt生成的token值已经超时，但该token对应cache中的k还是存在，则表示该用户一直在操作只是JWT的token失效了，程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
     * 4、当该用户这次请求jwt在生成的token值已经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
     * 注意： 前端请求Header中设置Authorization保持不变，校验有效性以缓存中的token为准。
     *       用户过期时间 = Jwt有效时间 * 2。
     *
     * @param token
     * @param sysUserEntity
     * @return
     */
    public boolean jwtTokenRefresh(String token, SysUserEntity sysUserEntity) {
        String cacheToken = (String) redissonClient.getBucket(RedisConstant.PREFIX_USER_TOKEN + DigestUtils.md5Hex(token)).get();
        log.info("用户token缓存,key:{}, value:{}", RedisConstant.PREFIX_USER_TOKEN + DigestUtils.md5Hex(token), cacheToken);
        if (StringUtils.isNotBlank(cacheToken)) {
            // 校验token有效性
            if (!jwtHelper.validateToken(cacheToken, sysUserEntity.getPassword())) {
                String newAuthorization = jwtHelper.generateToken(sysUserEntity);
                // 设置超时时间
                redissonClient.getBucket(RedisConstant.PREFIX_USER_TOKEN + DigestUtils.md5Hex(token))
                        .set(newAuthorization, configProperties.getJwt().getRedisExpiration(), TimeUnit.MINUTES);
                log.info("——————————用户在线操作, 更新token保证不掉线—————————jwtTokenRefresh——————— "+ token);
            }
            return true;
        }
        log.warn("用户token为空:{}", sysUserEntity.getLoginName());
        return false;
    }

    /**
     * 清除当前用户的权限认证缓存
     *
     * @param principals 权限信息
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
}
