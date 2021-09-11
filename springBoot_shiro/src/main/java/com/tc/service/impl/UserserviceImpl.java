package com.tc.service.impl;
import com.tc.Utils.RandomShrio;
import com.tc.dao.UserMapper;
import com.tc.pojo.User;
import com.tc.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author 谭铖
 * @date 2021/9/8 14:31
 */
@Service
@Transactional
public class UserserviceImpl implements userService{

    @Autowired
    UserMapper userDAO;
    /**
     * 登录
     * @param
     */
    @Override
    public User login(String username) {
        User user1 = userDAO.selectByPrimaryKey(username);
        return user1;
    }

    /**
     * 注册
     * @param user
     */
    @Override
    public boolean register(User user) {
        String salt = RandomShrio.Shiroran();
//      密码生成为md5
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
//        传入密码
        user.setPassword(md5Hash.toHex());
//       盐值
        user.setSalt(salt);
//        注册
        try {
            userDAO.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Set<String> getRolesByUserId(Integer id) {
        return userDAO.getRolesByUserId(id);
    }

    @Override
    public Set<String> getPremByUserId(Integer id) {
        return userDAO.getPremByUserId(id);
    }
}
