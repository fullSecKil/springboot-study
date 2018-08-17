package com.example.userinformanage.service;

import com.example.userinformanage.pojo.User;

import java.util.List;

public interface UserService {
    User getUser(String username);

    int addUser(User user);

    List<User> selectAll();

    User selectUserNameAndPassword(String username, String password);
}
