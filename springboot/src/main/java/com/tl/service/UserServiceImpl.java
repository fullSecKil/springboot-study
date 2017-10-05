package com.tl.service;

import com.tl.dao.UserMapper;
import com.tl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUser(String age) {
        return userMapper.getUser(age);
    }
}
