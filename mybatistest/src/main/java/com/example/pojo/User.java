package com.example.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String nickname;
    private String password;
    private String firstName;
    private String lastName;

    public User(){

    }

    public User(String nickname,String password){
        this.nickname = nickname;
        this.password = password;
    }
}
