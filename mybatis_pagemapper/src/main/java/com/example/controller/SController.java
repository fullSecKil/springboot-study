package com.example.controller;

import com.example.mybatis_pagemapper.mapper.UserMapper;
import com.example.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SController {
    @Autowired
    UserMapper userMapper;

/*    @GetMapping("/")
    @ResponseBody
    public List<User> index(){
        return userMapper.countByUsername("dusk");
    }*/

    @GetMapping("/show")
    @ResponseBody
    public PageInfo show(){
        userMapper.insertSelective(new User("ww","mv"));
        //userAdd();
        final PageInfo<Object> pageInfo = PageHelper.startPage(1,10).setOrderBy("id desc").doSelectPageInfo(()->this.userMapper.selectAll());
        return pageInfo;
    }

 /*   public void userAdd(){
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
        userMapper.addUser("ee","mv");
    }*/
}
