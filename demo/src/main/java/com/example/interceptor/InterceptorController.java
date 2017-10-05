package com.example.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterceptorController {
    @GetMapping("/hello2")
    public String helloPage(){
        return "Hello Spring Boot";
    }
}
