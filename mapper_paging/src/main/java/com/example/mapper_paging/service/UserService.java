package com.example.mapper_paging.service;

import com.example.mapper_paging.pojo.User;

import java.util.List;

public interface UserService {
    int countByUsername(String username);

    void insertSelective(User user);

    List<User> selectAll();
}
