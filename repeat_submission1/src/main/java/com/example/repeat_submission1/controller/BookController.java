package com.example.repeat_submission1.controller;

import com.example.repeat_submission1.annotation.LocalLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dusk
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @LocalLock(key = "book:arg[0]")
    @GetMapping
    public String query(@RequestParam String token){
        return "success - "+token;
    }

}
