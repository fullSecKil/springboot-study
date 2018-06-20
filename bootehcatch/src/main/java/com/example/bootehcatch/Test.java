package com.example.bootehcatch;

import com.example.bootehcatch.pojo.User;
import com.example.bootehcatch.repository.UserRepository;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test {
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    @org.junit.Test
    public void contextLoads() {
        User user1 = userRepository.findAllById(3L);
        log.info("[查询第一次] - [{}]", user1);

        User user2 = userRepository.findAllById(3L);
        log.info("[查询第二次] - [{}]", user2);
        user1.setUsername("wo");
        userRepository.save(user1);

        User user3 = userRepository.findAllById(3L);
        log.info("[查询第三次] - [{}]", user3);
    }
}
