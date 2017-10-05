package com.example.mybatis.controller;

import com.example.mybatis.pojo.Permission;
import com.example.mybatis.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PermissionController {
    @Autowired
    IPermissionService permissionService;

    @GetMapping("/list")
    public List<Permission> list(){
        return permissionService.selectAll();
    }
    @GetMapping("/save")
    public String save(){
        permissionService.insert(new Permission("m2","24","3","/w",1));
        return "保存成功";
    }
}
