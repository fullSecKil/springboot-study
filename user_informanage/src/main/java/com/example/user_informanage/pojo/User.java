package com.example.user_informanage.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String realname;

    private String email;

    private String photo;

    private String title;

    public User(){};

    public User(String username, String password, String email, String realname){
        this.username = username;
        this.password = password;
        this.email = email;
        this.realname = realname;
    }

}
