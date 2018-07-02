package com.example.mapper_paging.service.Impl;

import com.example.mapper_paging.dao.UserMapper;
import com.example.mapper_paging.pojo.User;
import com.example.mapper_paging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int countByUsername(String username) {
        return userMapper.countByUsername(username);
    }

    @Override
    public void insertSelective(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
