package com.tc.dao;

import com.tc.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author 谭铖
 * @date 2021/9/8 14:25
 * 登录注册
 */
@Mapper
public interface UserMapper {

    int register(User user);

    User selectByPrimaryKey(String username);

    Set<String> getRolesByUserId(Integer id);

    Set<String> getPremByUserId(Integer id);
}
