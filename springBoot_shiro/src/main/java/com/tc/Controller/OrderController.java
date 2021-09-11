package com.tc.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

/**
 * @author 谭铖
 * @date 2021/9/9 14:32
 */
@Controller
public class OrderController {

    @GetMapping("save")
    @RequiresRoles(value = {"admin","user"},logical = Logical.OR)
    public String save(){
//        获取主体对象
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }
        return "index";
    }
}
