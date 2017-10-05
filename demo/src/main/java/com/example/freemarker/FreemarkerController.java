package com.example.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class FreemarkerController {

    @GetMapping("/me")
    public String helloPage(ModelMap model){
        //int i=1/0;
        //List invent = Arrays.asList("xue","rui","ni","zui","shuai");
        Map<String,String> invent= new HashMap<>();
        invent.put("name","xue");
        invent.put("context","zuishuai");
        model.addAttribute("invent",invent);
        //model.addAttribute("name","xueruizuishai");
        return "hello";
    }
}

