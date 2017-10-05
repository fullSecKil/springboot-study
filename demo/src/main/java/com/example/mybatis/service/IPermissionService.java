package com.example.mybatis.service;

import com.example.mybatis.pojo.Permission;

import java.util.List;

public interface IPermissionService {
    public List<Permission> selectAll();

    public void insert(Permission permission);
}
