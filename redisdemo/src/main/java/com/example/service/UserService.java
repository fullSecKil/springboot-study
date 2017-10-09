package com.example.service;

import com.example.pojo.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface UserService {
    @Cacheable(value = "users", key = "'user_'+#id")
    User getUser(String id);

    @CacheEvict(value = "users", key = "'user_'+id",condition = "#id!=1")
    void deletUser(String id);
}
