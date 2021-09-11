package com.tc.service;

import com.tc.pojo.User;

import java.util.Set;

/**
 * @author 谭铖
 * @date 2021/9/8 14:28
 */
public interface userService  {

    /**
     * 登录判断
     * @param username
     * @return
     */
    public User login(String username);

    /**
     * 注册操作
     * @param user
     * @return
     */
    public boolean register(User user);

    /**
     * 角色判断
     * @param id
     * @return
     */
    Set<String> getRolesByUserId(Integer id);

    /**
     * 权限判断
     * @param id
     * @return
     */
    Set<String> getPremByUserId(Integer id);

}
