package com.tc.Controller;

import com.tc.dao.UserMapper;
import com.tc.pojo.User;
import com.tc.service.userService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 谭铖
 * @date 2021/9/7 19:58
 * 登录页面跳转
 */
@Controller
public class    LoginPage {

    @Autowired
    private userService userService;


    @GetMapping({"/","login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(User user){
        User login = userService.login(user.getUsername());
        try {
            if (user.getUsername().equals(login.getUsername())) {
                Md5Hash md5Hash = new Md5Hash(user.getPassword(),login.getSalt(),1024);
                String s = md5Hash.toHex();
                UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),s);
                Subject subject = SecurityUtils.getSubject();
                try {
                    subject.login(token);
                    System.out.println("登录成功");
                    return "redirect:/index";
                } catch (AuthenticationException e) {
                    e.printStackTrace();
                    System.out.println("账号或密码错误");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("控制指标");
        }
        return "login";
    }
    @GetMapping("/index")
    public String indexPage(){
        return "index";
    }
    @GetMapping("/logout")
    public String logout(){
//        获取登录对象
        Subject subject = SecurityUtils.getSubject();
//        判断用户是否登录 false为未登录 反之true
        if (subject.isAuthenticated()) {
//            退出登录
            subject.logout();
        }
        return "redirect:/";
    }


    /**
     * 注册register
     */
    @PostMapping("register")
    public String Register(User user){
        boolean register = userService.register(user);
        if (register) {
            System.out.println("注册成功");
            return "login";
        }else{
            System.out.println("shi版");
        }
        return "register";
    }
}
