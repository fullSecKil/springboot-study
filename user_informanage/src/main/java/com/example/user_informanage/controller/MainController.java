package com.example.user_informanage.controller;

import com.example.user_informanage.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginform")
    public String showIndex(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute(new User("xuerui","123","en2213@163.com","xue"));
        model.addAttribute(userDetails.getUsername());
        System.out.println("loginform");
        return "index";
    }

    @GetMapping("/register")
    public String registerUser(Model model){
        model.addAttribute(new User());
        return "registeration";
    }

    @GetMapping("/a")
    @ResponseBody
    public String showA(){
        return "AAAAAAAAAAA";
    }

    @PostMapping("/registerPut")
    @ResponseBody
    public User showRegisterPut(@RequestPart("profilePicture") MultipartFile file,@ModelAttribute("loginUser") User user){
        //user.setPhoto(file.getOriginalFilename());
        return user;
    }
}
