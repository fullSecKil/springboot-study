package com.example.userinformanage.service.impl;

import com.example.userinformanage.dao.UserMapper;
import com.example.userinformanage.pojo.User;
import com.example.userinformanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectUserNameAndPassword(String username, String password) {
        return (User) userMapper.selectUserNameAndPassword(username, password);
    }

}
