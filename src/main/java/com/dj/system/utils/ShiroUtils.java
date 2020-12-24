package com.dj.system.utils;

import com.dj.system.model.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author wxl
 * @date 2020-03-20 下午12:56
 * @company www.dfdk.com.cn
 */
public class ShiroUtils {

    /**  加密算法 */
    public final static String HASHALGORIRHMNAME = "SHA-256";
    /**  循环次数 */
    public final static int HASHLTERATIONS = 16;

    public static String sha256(String password,String salt) {
        return new SimpleHash(HASHALGORIRHMNAME, password, salt, HASHLTERATIONS).toString();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SysUserEntity getUserEntity() {
        return (SysUserEntity)SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getUserEntity().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getIp()
    {
        return getSubject().getSession().getHost();
    }
}
