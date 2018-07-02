package com.example.restfulswagger.controller;

import com.example.restfulswagger.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/users")
@Api(tags = "1.1", description = "用户管理", value = "用户管理")
public class UserController {
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation(value = "获取用户列表", notes = "")
    @GetMapping
    public List<User> getUserList(){
        List<User> r = new ArrayList<>(users.values());
        return r;
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name="user", value = "用户详细实体类user", required = true, dataType = "User")
    @PostMapping
    public String postUser(@ModelAttribute User user){
        users.put(user.getId(), user);

        return "success";
    }
}
