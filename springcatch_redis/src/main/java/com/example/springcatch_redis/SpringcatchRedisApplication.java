package com.example.springcatch_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringcatchRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcatchRedisApplication.class, args);
    }
}
