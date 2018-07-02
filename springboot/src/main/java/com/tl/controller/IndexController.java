package com.tl.controller;

import com.tl.pojo.User;
import com.tl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  IndexController
 *
 * @author Dusk
 * @date  2018/6/27
 */
@RestController
public class IndexController {

    @Autowired
    UserService userService;

    @GetMapping("/show")
    public List<User> getUser(String  age){
        return userService.getUser(age);
    }

    //@RequestMapping(value = {"/index","/show"}, method = RequestMethod.GET)
    @GetMapping(value ={"/index"} )
    public Map<String,String> index(){
     Map map = new HashMap<>();
     map.put("悟空","是个猴子");
     map.put("鲁班","是个。。");
     return map;
    }
}
