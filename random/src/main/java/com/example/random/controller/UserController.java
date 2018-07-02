package com.example.random.controller;

import com.example.random.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    //创建线程安全Map
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @GetMapping
    public List<User> getUserList(){
        List<User> r = new ArrayList<>(users.values());
        return r;
    }

    @PostMapping
    public String postUser(@ModelAttribute User user){
        // 处理"/users/"的POST请求，用来创建User
        users.put(user.getId(), user);
        return "success";
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id){
        return users.get(id);
    }

    @PutMapping(value ="/{id}")
    public String putUser(@PathVariable Long id, @ModelAttribute User user){
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        //处理/users/{id}的 Delete请求, 用来删除User
        users.remove(id);
        return "success";
    }
}
