package com.example.userinformanage.controller;

import com.example.userinformanage.config.RabbitConfig;
import com.example.userinformanage.pojo.User;
import com.example.userinformanage.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dusk
 */

@Controller
public class MainController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;


    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginform")
    public String showIndex(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute(userDetails.getUsername());
        System.out.println("loginform");
        return "index";
    }

    @GetMapping("/register")
    public String registerUser(Model model){
        model.addAttribute(new User());
        return "registeration";
    }

    /**
     *  从properties中取出图片存放的地址
     */
    @Value("${spring.servlet.multipart.location}")
    String photoPath;

    /**
     *  Validated 方法参数中即为验证参数对象
     *   ModelAttribute("loginUser")
     */

    @PostMapping("/registerPut")
    @ResponseBody
    public User showRegisterPut(@RequestPart("profilePicture") MultipartFile file, @Validated  User user) {

        String realPassword = user.getPassword();
        user.setPhoto(photoPath+file.getOriginalFilename());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(userService.addUser(user)>0){
            // 图片上传
            try {
                file.transferTo(new File(file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> context = new HashMap<>();
            context.put("username", user.getUsername());
            context.put("realname", user.getRealname());
            context.put("password", realPassword);
            context.put("receiver", user.getEmail());

            // 当前时间，清除毫秒
            context.put("time", LocalDateTime.now().withNano(0).toString());

            // 发送注册邮件委托给rabbitmq队列
            this.rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_MAIL_EXCHANGE, "mail.register", context);
        }
        return user;
    }
}
