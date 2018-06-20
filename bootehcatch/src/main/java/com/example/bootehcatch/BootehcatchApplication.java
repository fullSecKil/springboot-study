package com.example.bootehcatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.Entity;

@SpringBootApplication
@EnableCaching
public class BootehcatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootehcatchApplication.class, args);
    }
}
