package com.example.rabbitmq_queue.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    public Book(){}

    public Book(String id, String name){
        this.id = id;
        this.name = name;
    }
}
