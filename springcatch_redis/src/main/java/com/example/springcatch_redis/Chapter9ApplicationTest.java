package com.example.springcatch_redis;

import com.example.springcatch_redis.pojo.User;
import com.example.springcatch_redis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Chapter9ApplicationTest {
    private static final Logger log = LoggerFactory.getLogger(Chapter9ApplicationTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void get(){
        final User user2 = userService.getUser(1L);
        log.info("[get] - [{}]", user2);
        final User user = userService.saveOrUpdate(new User(5L,"u5","p5"));
        log.info("[saveOrUpdate] - [{}]", user);

         User user1 = userService.getUser(5L);
        log.info("[get] - [{}]", user1);
        userService.delete(5L);
        user1 = userService.getUser(5L);
        log.info("[get] - [{}]", user1);
    }
}
