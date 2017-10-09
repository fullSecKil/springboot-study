package com.example.redisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.service","com.example.redisdemo"})
public class RedisdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisdemoApplication.class, args);
    }
}
