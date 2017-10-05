package com.example.mybatis.service.impl;

import com.example.mybatis.mapper.PermissionMapper;
import com.example.mybatis.pojo.Permission;
import com.example.mybatis.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional          //事务注解
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public void insert(Permission permission) {
        permissionMapper.insert(permission);
        //int i = 1/0;
    }


}
