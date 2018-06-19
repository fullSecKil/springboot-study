package com.example.user_informanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "com.example.user_informanage.dao")
public class UserInformanageApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserInformanageApplication.class, args);
    }
}
