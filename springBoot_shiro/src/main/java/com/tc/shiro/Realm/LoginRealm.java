package com.tc.shiro.Realm;

import com.tc.dao.UserMapper;
import com.tc.pojo.User;
import com.tc.service.userService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Set;

/**
 * @author 谭铖
 * @date 2021/9/7 19:19
 */
@Component
@Slf4j
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private userService userService;
    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        获取对象用户名
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("权限授权用户："+primaryPrincipal);
        User login = this.userService.login(primaryPrincipal);
        if (!ObjectUtils.isEmpty(login)) {
//            查询用户角色
            Set<String> perms = this.userService.getPremByUserId(login.getId());
            System.out.println("角色权限："+perms);
//            查询角色权限(如果不使用set集合会出现重复权限 set可以去重 当然底层也是set)
            Set<String> roles = this.userService.getRolesByUserId(login.getId());
            System.out.println("用户角色："+roles);
//            创建授权器
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//            传入授权角色
            simpleAuthorizationInfo.setRoles(roles);
//            传入授权角色操作
            simpleAuthorizationInfo.setStringPermissions(perms);
//            返回
            return simpleAuthorizationInfo;
        }
       return null;
    }

    /**
     * 登录拦截
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        查询到用户名
        String principal = (String) token.getPrincipal();
//        数据库查询
        User user = userService.login(principal);
        if (user == null) {
            throw new UnknownAccountException();
        }
//        判断对象是否为空
        if (!ObjectUtils.isEmpty(user)) {
//           传入密码md5+salt随机盐
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()),getName());
//            返回
            return info;
        }
       return null;
    }
}
