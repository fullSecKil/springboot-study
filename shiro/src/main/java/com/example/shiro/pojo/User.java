package com.example.shiro.pojo;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private String roleName;
    private boolean locked;


    public User(){}

    public User(Long id, String username, String password, String roleName, boolean locked){
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleName = roleName;
        this.locked = locked;
    }
}
