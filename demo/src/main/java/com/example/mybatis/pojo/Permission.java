package com.example.mybatis.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter@Setter@ToString
public class Permission {

    private Integer id;
    private String username;
    private String age;
    private String password;
    private String image;
    private Integer isactivity;

    public Permission(String username,String age,String password,String image,Integer isactivity){
        this.username = username;
        this.age = age;
        this.password = password;
        this.image = image;
        this.isactivity = isactivity;
    }
}
