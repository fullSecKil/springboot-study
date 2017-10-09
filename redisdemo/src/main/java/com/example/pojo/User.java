package com.example.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID =1L;

    private String id;

    private String name;

    private int age;

    public User(){
        super();
    }

    public User(String id, String name, int age){
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
