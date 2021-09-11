package com.tc;

import com.tc.dao.UserMapper;
import com.tc.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	UserMapper userDAO;
	@Test
	void contextLoads() {
//		System.out.println("用户角色："+userDAO.getRolesByUserId(1));
//		System.out.println("用户角色权限："+userDAO.getPremByUserId(2));
		System.out.println(userDAO.selectByPrimaryKey("admin"));
	}


}
