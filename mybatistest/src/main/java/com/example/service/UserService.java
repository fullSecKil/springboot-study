package com.example.service;

import com.example.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findByUsername(String nickname);
    boolean updateUser(Integer id);
    int insert(User user);
}
