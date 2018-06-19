package com.example.spring_scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SpringScheduledApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringScheduledApplication.class, args);
    }

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }
}
