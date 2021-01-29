package com.dj.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.dj.system.realm.DKRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wxl
 * @date 2020-03-20 下午12:24
 * @company www.dfdk.com.cn
 */
@Configuration
public class ShiroConfig {

    private static final Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean (SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setUnauthorizedUrl("/404");
        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/dk/**", "anon");
        filterChainDefinitionMap.put("/plugins/layui/**", "anon");
        filterChainDefinitionMap.put("/swagger/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/swagger", "anon");
        filterChainDefinitionMap.put("/doc.html", "anon");
        filterChainDefinitionMap.put("/tokenLogin", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/main", "anon");
        filterChainDefinitionMap.put("/sysUser", "anon");
        filterChainDefinitionMap.put("/addUserPage", "anon");
        filterChainDefinitionMap.put("/editUserPage", "anon");
        filterChainDefinitionMap.put("/sysMenu", "anon");
        filterChainDefinitionMap.put("/addMenuPage","anon");
        filterChainDefinitionMap.put("/editMenuPage","anon");
        filterChainDefinitionMap.put("/sysRole","anon");
        filterChainDefinitionMap.put("/addRolePage","anon");
        filterChainDefinitionMap.put("/editRolePage","anon");
        filterChainDefinitionMap.put("/sysDept","anon");
        filterChainDefinitionMap.put("/addDeptPage","anon");
        filterChainDefinitionMap.put("/editDeptPage","anon");
        filterChainDefinitionMap.put("/sysPost","anon");
        filterChainDefinitionMap.put("/addPostPage","anon");
        filterChainDefinitionMap.put("/editPostPage","anon");
        filterChainDefinitionMap.put("/websocket/**","anon");

        filterChainDefinitionMap.put("/**", "authc");

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        // <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        filterChainDefinitionMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    /**
     * 配置shiro生命周期
     * @author wxl
     * @date 2020-03-20
     * @return
     **/
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor () {
        return new LifecycleBeanPostProcessor();
    }

    @Bean("userRealm")
    public DKRealm userRealm () {
        return new DKRealm();
    }

    /**
     * 下面的代码是添加注解支持
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        /**
         * 解决重复代理问题 github#994
         * 添加前缀判断 不匹配 任何Advisor
         */
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        defaultAdvisorAutoProxyCreator.setAdvisorBeanNamePrefix("_no_advisor");
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 配置安全管理器
     * @author wxl
     * @date 2020-03-20
     * @return
     **/
    @Bean("securityManager")
    public SecurityManager securityManager (DKRealm userRealm) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setSessionManager(sessionManager());
//        securityManager.setRealm(userRealm);
//        securityManager.setRememberMeManager(null);
//        return securityManager;

            DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
            securityManager.setRealm(userRealm);


             /** 关闭shiro自带的session，详情见文档
             * http://shiro.apache.org/session-management.html#SessionManagement-
             * StatelessApplications%28Sessionless%29*/

            DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
            DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
            defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
            subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
            securityManager.setSubjectDAO(subjectDAO);
            //自定义缓存实现,使用redis
            securityManager.setCacheManager(redisCacheManager());
            return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor (SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager redisCacheManager() {
        log.info("===============(1)创建缓存管理器RedisCacheManager");
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //redis中针对不同用户缓存(此处的id需要对应user实体中的id字段,用于唯一标识)
        redisCacheManager.setPrincipalIdFieldName("userId");
        //用户权限信息缓存时间 考虑是否跟刷新token同步更新
        redisCacheManager.setExpire(60 * 60 * 24);
        return redisCacheManager;
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        log.info("===============(2)创建RedisManager,连接Redis..URL= " + "127.0.0.1" + ":" + "6379");
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1");
        redisManager.setPort(6379);
        redisManager.setTimeout(0);
        if (!StringUtils.isEmpty("")) {
            redisManager.setPassword("");
        }
        return redisManager;
    }

}
