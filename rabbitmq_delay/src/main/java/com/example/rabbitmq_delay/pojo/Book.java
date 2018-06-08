package com.example.rabbitmq_delay.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String username;

    public Book(){}
    public Book(String id, String username){
        this.id = id;
        this.username = username;
    }
}
