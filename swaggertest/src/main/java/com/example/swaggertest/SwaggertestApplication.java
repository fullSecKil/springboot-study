package com.example.swaggertest;

import com.battcn.swagger.annotation.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableSwagger2Doc
public class SwaggertestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggertestApplication.class, args);
    }
}
