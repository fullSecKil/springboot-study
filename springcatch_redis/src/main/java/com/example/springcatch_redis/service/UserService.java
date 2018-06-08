package com.example.springcatch_redis.service;

import com.example.springcatch_redis.pojo.User;

public interface UserService {
    User saveOrUpdate(User user);

    User getUser(Long id);

    void delete(Long id);
}

