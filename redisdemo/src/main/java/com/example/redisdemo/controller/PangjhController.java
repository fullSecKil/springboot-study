package com.example.redisdemo.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PangjhController {
    @Autowired
    private StringRedisTemplate template;

    @Autowired
    UserService userService;

    @GetMapping("/getUser")
    public User getUser(){
        User user = userService.getUser("xianggu");
        return user;
    }

    @GetMapping("/deleteUser")
    public String deleteUser(){
        userService.deletUser("xianggu");
        return "执行删除";
    }

    @RequestMapping("/setValue")
    public String setValue(){
        if(!template.hasKey("shab")){
            template.opsForValue().append("shab","我是薛瑞");
            return "使用redis缓存保存数据成功";
        }else {
            template.delete("shab");
            return "key已存在";
        }
    }
    @RequestMapping("/getValue")
    public String getValue(){
        if(!template.hasKey(("shab"))){
            return "key不存在，请先保存数据";
        }else {
            String shab = template.opsForValue().get("shab");   //根据key获取缓存中的val
            return "缓存中的数据：shab="+shab;
        }
    }
}
