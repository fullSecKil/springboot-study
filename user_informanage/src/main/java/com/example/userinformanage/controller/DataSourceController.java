package com.example.userinformanage.controller;

import com.example.userinformanage.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DataSourceController {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @GetMapping("/druidDatat")
    public String dataSourcePage() throws SQLException {
        System.out.println(dataSource.getConnection());
        System.out.println(dataSource);
        return "druidDataSo";
    }

    @PostMapping(value = "/checkName", produces = "application/json;charset=UTF-8")
    public String checkName(@RequestParam String username){

        if(username.length()<6){
            return "";
        }

        System.out.println("姓名是："+username);
        boolean result = false;
        System.out.println("判断是否有用户名：" + userService.getUser(username));
        //result = userService.selectByUserName(username);
        if(userService.getUser(username) == null){
            result = true;
        }

        Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();

        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }

}
