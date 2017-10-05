package com.example.freemarker;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    public String handlerError(Exception ex, HandlerMethod handlerMethod, ModelMap model){
        System.out.println("统一异常处理");
        System.out.println(ex.getMessage());
        System.out.println(handlerMethod.getBean().getClass());
        System.out.println(handlerMethod.getMethod().getName());

        Map<String,String> message = new  HashMap<>();
        message.put("message1",ex.getMessage());
        message.put("messageClass",handlerMethod.getBean().getClass().toString());
        message.put("messageMethod",handlerMethod.getMethod().getName());

        model.addAttribute("message",message);

        return "5xx";
    }
}
