package com.example.service.UserServiceImpl;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findByUsername(String nickname) {
        return userMapper.findByUsername(nickname);
    }

    @Override
    public boolean updateUser(Integer id) {
        return userMapper.updateUser(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
