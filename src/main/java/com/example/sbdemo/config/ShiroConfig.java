package com.example.sbdemo.config;

import com.example.sbdemo.shiro.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description TODO
 * @Author LL
 * @Date 2019-06-05 14:25
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {

    /**
     * @Author: LL
     * @Date: 2019-06-05 14:29
     * @param:
     * @return:
     * @Description: cookie对象，设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     */
    @Bean
    public SimpleCookie getSimpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        // 对应前端返回的 checkbox的name=rememberMe
        simpleCookie.setName("rememberMe");
        //记住我cookie生效时间30天，单位秒
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * @Author: LL
     * @Date: 2019-06-05 14:35
     * @param:
     * @return:
     * @Description: cookie管理对象，生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     */
    @Bean
    public CookieRememberMeManager getCookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(getSimpleCookie());
        return cookieRememberMeManager;
    }

    /**
     * @Author: LL
     * @Date: 2019-06-05 14:38
     * @param:
     * @return:
     * @Description: 开启MD5加密
     */
    @Bean
    public HashedCredentialsMatcher getMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义Realm密码验证与加密
     *
     * @return
     */
    @Bean
    public CustomRealm getCustoRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(getMatcher());
        return customRealm;
    }

    /**
     * 创建SecurityManager环境
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getCustoRealm());
        securityManager.setRememberMeManager(getCookieRememberMeManager());
        return securityManager;
    }

    /**
     * Shiro在web项目中的过滤
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(getSecurityManager());
        /**
         *  只有在下面配置路径访问权限，Shiro才会执行自动跳转。
         *  如果使用Shiro注解权限，就只会报异常，
         *  就只能采用统一异常处理的方法。
         */
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/user/exit", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/user/**", "authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        filterFactoryBean.setLoginUrl("/user/tologin");
        // 登录成功后要跳转的链接
        filterFactoryBean.setSuccessUrl("/user/index");
        //未授权界面;
        filterFactoryBean.setUnauthorizedUrl("/403");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filterFactoryBean;
    }

    /**
     * 保证Shiro的声明周期
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro授权生效
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        return new AuthorizationAttributeSourceAdvisor();
    }
}