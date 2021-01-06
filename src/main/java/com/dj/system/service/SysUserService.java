package com.dj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.common.QuerySysUserRequest;
import com.dj.common.ResEntity;
import com.dj.common.SysUserRequest;
import com.dj.system.model.SysRoleEntity;
import com.dj.system.model.SysUserEntity;
import com.dj.system.model.SysUserPostEntity;
import com.dj.system.model.SysUserRoleEntity;
import com.dj.system.vo.ResVo;
import com.dj.system.vo.SysUserVo;

import java.util.List;

/**
 * @author wxl
 * @date 2020-03-17 上午9:24
 * @company www.dfdk.com.cn
 */
public interface SysUserService extends IService<SysUserEntity> {


    /**
     * 根据用户名查找
     *
     * @param userName
     * @return
     * @author wxl
     * @date 2020-03-20
     **/
    SysUserEntity getByUserName(String userName);

    /**
     * 分页查询
     *
     * @param userEntity  用户实体
     * @param currentPage 当前页
     * @param countPage   每页的页数
     * @return Resvo
     * @author wxl
     * @date 2020-03-20
     **/
    ResVo<SysUserEntity> page(SysUserEntity userEntity, Long currentPage, Long countPage);

    /**
     * 添加用户
     *
     * @param userEntity     user
     * @param userPostEntity userPost
     * @param userRoleEntity userRole
     * @return boolean
     * @author wxl
     * @date 2020-03-20
     **/
    boolean addUser(SysUserEntity userEntity, SysUserPostEntity userPostEntity, SysUserRoleEntity userRoleEntity);

    /**
     * 修改用户
     *
     * @param userEntity     user
     * @param userPostEntity userPost
     * @param userRoleEntity userRole
     * @return boolean
     * @author wxl
     * @date 2020-03-20
     **/
    boolean updateUser(SysUserEntity userEntity, SysUserPostEntity userPostEntity, SysUserRoleEntity userRoleEntity);

    /**
     * 用户新增或者修改
     * @param request 用户请求参数
     * @return
     */
    ResEntity<Boolean> saveOrUpdateSysUser(SysUserRequest request);


    ResEntity<Boolean> deleteSysUser(SysUserEntity sysUserEntity);
    /**
     * 修改密码
     *
     * @param userEntity 用户实体
     * @return boolean
     * @author wxl
     * @date 2020-03-20
     **/
    boolean updatePwd(SysUserEntity userEntity);

    /**
     * 查询用户分页列表
     * @param request
     * @return
     */
    ResEntity<?> querySysUserPage(QuerySysUserRequest request);

    /**
     * 用户登录接口
     *
     * @param sysUserVo
     * @return
     */
    ResEntity<?> tokenLogin(SysUserVo sysUserVo);

    /**
     * 请求头数据
     *
     * @param token
     * @return
     */
    ResEntity<?> tokenLogout(String token);

    /**
     * 请求头数据
     *
     * @param userId
     * @return
     */
    List<SysRoleEntity> getUserRoles(Long userId);

    /**
     * 请求头数据
     *
     * @param loginName
     * @return
     */
    SysUserEntity getSysUserByLoginName(String loginName);
}
