package com.example.controller;

import com.example.pojo.Resource;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public List<Resource> respUser(){
        return userRepository.findAllById(2);
    }

    @GetMapping("/all")
    public List<Resource> findAll(){
        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }

}
