package com.example.controller;

import com.example.pojo.Book;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class ValidateController {
    @GetMapping("/vali1")
    public String test2(@NotBlank(message = "name不能为空") @Length(min=2, max = 10, message = "name 长度必须在 {min} - {max} 之间") String name){
        return "success";
    }

    @GetMapping("/vali3")
    public String test3(@Validated Book book) {
        return "success";
    }
}
