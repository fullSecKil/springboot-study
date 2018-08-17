package com.example.shiro.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public String get(){
        return "get....";
    }

    @RequiresRoles(value = {"admin", "test"}, logical = Logical.OR)
    @GetMapping("/query")
    public String query(){
        return "query....";
    }

    @GetMapping("/find")
    public String find(){
        return "find....";
    }
}
