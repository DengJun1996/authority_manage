package com.dj.constants;

import com.dj.system.realm.DKRealm;

/**
 * redis常量
 * @author jetlag
 * @date 2020/4/29
 **/
public class RedisConstant {

    /**
     * 项目名称
     */
    private final static String PROJECT = "dn_station_";

    private final static String POWER_CUT_EVENT = PROJECT + "power_cut_event_%s";

    /** 登录用户Token令牌缓存KEY前缀 */
    public static final String PREFIX_USER_TOKEN  = "prefix_user_token_";

    /** 用户权限缓存前缀 */
    public static final String PREFIX_USER_PERMISSIONS = "shiro:cache:%s.authorizationCache:%s";

    /** 数据锁前缀 */
    public static final String PREFIX_DATA_LOCK = PROJECT + "data_lock_%s";
    /** 数据锁过期时间 单位：毫秒 */
    public static final int PREFIX_DATA_LOCK_OUT_TIME = 1000;

    /**
     * 获取用户权限前缀缓存 @link
     * @param value 系统用户ID {@link com.dj.system.model.SysUserEntity}
     * @return
     */
    public static String getPrefixUserPermissions(String value) {
        String packName = DKRealm.class.getName();
        return String.format(PREFIX_USER_PERMISSIONS, packName, value);
    }

    /**
     * 获取数据锁前缀 @link
     * @param value 锁类型
     * @return
     */
    public static String getPrefixDataLock(String value) {
        return String.format(PREFIX_DATA_LOCK, value);
    }

}
