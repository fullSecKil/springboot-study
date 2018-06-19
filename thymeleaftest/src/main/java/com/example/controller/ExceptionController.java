package com.example.controller;

import com.example.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {
    @GetMapping("/test3")
    public String test3(Integer num){
        if(num==null) {
            throw new CustomException(400, "null不能空");
        }
            int i = 10/num;
            return "result"+i;
    }
}
