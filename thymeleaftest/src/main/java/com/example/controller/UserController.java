package com.example.controller;

import com.example.pojo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public String index(Model model){
        Person single = new Person("hyj",21);
        List<Person> people = Arrays.asList(new Person("dlp",22),new Person("tq",23),new Person("mk",24));

        model.addAttribute(single);
        model.addAttribute(people);
        return "index";
    }

}
