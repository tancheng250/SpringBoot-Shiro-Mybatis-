package com.tc.conf;
import com.tc.shiro.Realm.LoginRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author 谭铖
 * @date 2021/9/7 19:17
 */
@Configuration
public class ShiroConfig {

    /**
     * realm自定义
     * @return
     */
    @Bean
    Realm getLoginRealm(){
        LoginRealm loginRealm = new LoginRealm();
//        定制MD5管理
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        设置MD5
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
//        添加散列
        hashedCredentialsMatcher.setHashIterations(1024);
        return loginRealm;
    }

    /**
     * 安全管理器
     * @return
     */
    @Bean
    DefaultWebSecurityManager getDefaultSecurityManager(){
//        创建
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        传入自定义的realm
        securityManager.setRealm(getLoginRealm());
        return securityManager;
    }

    /**
     *  ShiroFilterFactoryBean 实例了，替代它的是 ShiroFilterChainDefinition ，在这里定义 Shiro 的路径匹配规则即可。
     *  拦截器
     * @return
     */
    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition(){
//        创建过滤对象
        DefaultShiroFilterChainDefinition filterChainDefinition = new DefaultShiroFilterChainDefinition();
        HashMap<String, String> hashMap = new LinkedHashMap<>();
        //anon：无需认证即可访问。
        //authc：需要认证才可访问。
        //user：点击“记住我”功能可访问。
        hashMap.put("/login","anon");
        hashMap.put("/register","anon");
        hashMap.put("/register.jsp","anon");
        hashMap.put("/**","authc");
//        添加至过滤
        filterChainDefinition.addPathDefinitions(hashMap);
        return filterChainDefinition;
    }
}
