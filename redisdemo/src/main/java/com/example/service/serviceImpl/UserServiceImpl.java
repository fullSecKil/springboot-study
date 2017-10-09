package com.example.service.serviceImpl;

import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(String id) {
        System.out.println(id+"读取实体类");
        User user = new User(id,"香菇",21);
        return user;
    }

    @Override
    public void deletUser(String id) {
        System.out.println(id+"进入实现类删除数据！");
    }
}
