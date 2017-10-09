package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MyBatisController {

    @Autowired
    UserService userService;

    @GetMapping("/show")
    @ResponseBody
    public List<User> show(String nickname){
        return userService.findByUsername(nickname);
    }

    @GetMapping("/update")
    public String update(Integer id){
        if(userService.updateUser(id)){
            return"redirect:/show?nickname=dusk";
        }
        return "失败！";
    }

    @GetMapping("/insert")
    public String insert(String nick){
        if(userService.insert(new User(nick,"222"))>0)
        return"redirect:/show?nickname={nick}";
        return "失败！";
    }
}
