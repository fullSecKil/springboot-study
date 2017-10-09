package com.example.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_user")
public class User {
    private static final long serialVersionUID = 8655851615465363473L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    public  User(){

    }
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
