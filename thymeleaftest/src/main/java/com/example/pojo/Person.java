package com.example.pojo;

import lombok.Data;

@Data
public class Person {
    private String name;
    private Integer age;

    public Person(String name,Integer gae) {
        this.name=name;
        this.age=gae;
    }
}
