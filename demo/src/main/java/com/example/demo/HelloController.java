package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {
    @Autowired
    private ApplicationArguments applicationArguments;

    @GetMapping("/hello")
    public String helloPage(){
        System.out.println(applicationArguments.getNonOptionArgs());
        return "Hello Spring Boot";
    }
}
