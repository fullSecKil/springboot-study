package com.example.mybatis_pagemapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.example.mybatis_pagemapper.mapper")

@ComponentScan(basePackages = "com.example")
public class MybatisPagemapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPagemapperApplication.class, args);
    }
}
