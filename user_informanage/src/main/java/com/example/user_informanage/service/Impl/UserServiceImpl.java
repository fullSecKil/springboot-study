package com.example.user_informanage.service.Impl;

import com.example.user_informanage.dao.UserMapper;
import com.example.user_informanage.pojo.User;
import com.example.user_informanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.saveUser(user);
    }
}
