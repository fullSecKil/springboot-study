package com.example.springcatch_redis.service.Impl;

import com.example.springcatch_redis.pojo.User;
import com.example.springcatch_redis.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
    private static final Map<Long, User> DATABASS = new HashMap<>();

    static {
        DATABASS.put(1L,new User(1L,"u1","p1"));
        DATABASS.put(1L,new User(2L,"u2","p2"));
        DATABASS.put(1L,new User(3L,"u3","p3"));
    }

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @CachePut(value = "user", key = "#user.id")
    @Override
    public User saveOrUpdate(User user) {
        log.info("进入saveOrUpdate方法");
        DATABASS.put(user.getId(),user);
        return user;
    }

    @Cacheable(value = "user", key = "#id")
    @Override
    public User getUser(Long id) {
        log.info("进入get方法");
        return DATABASS.get(id);
    }
    @CacheEvict(value = "user", key = "#id", condition = "#id!=5L")
    @Override
    public void delete(Long id) {
        DATABASS.remove(id);
        log.info("进入delete方法");
    }
}
