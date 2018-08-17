package com.example.shiro.controller;

import com.example.shiro.config.ShiroConfiguration;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(ShiroConfiguration.class);

    @GetMapping(value = "/hello")
    public String hello(){
        log.info("不登录也可以访问");
        return "hello>>>";
    }

    @GetMapping(value = "/index")
    public String index(){
        log.info("登陆成功>>>>");
        return "index...";
    }

    @GetMapping(value = "/denied")
    public String denied(){
        log.info("小伙子权限不足，别做无谓的挣扎！");
        return "denied...";
    }

    @GetMapping(value = "login")
    public String login(String username, String password, RedirectAttributes model){
        Subject sub = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            sub.login(token);
        } catch (UnknownAccountException e){
            log.error("对用户[{}]进行登录验证,验证未通过,用户不存在", username);
            token.clear();
            return "UnknownAccountException";
        }catch (LockedAccountException lae) {
            log.error("对用户[{}]进行登录验证,验证未通过,账户已锁定", username);
            token.clear();
            return "LockedAccountException";
        } catch (ExcessiveAttemptsException e) {
            log.error("对用户[{}]进行登录验证,验证未通过,错误次数过多", username);
            token.clear();
            return "ExcessiveAttemptsException";
        } catch (AuthenticationException e) {
            log.error("对用户[{}]进行登录验证,验证未通过,堆栈轨迹如下", username, e);
            token.clear();
            return "AuthenticationException";
        }
        return "success";
    }
}
