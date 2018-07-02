package com.example.repeat_submission;

import com.example.repeat_submission.interceptor.CacheKeyGenerator;
import com.example.repeat_submission.interceptor.Impl.LockKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RepeatSubmissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepeatSubmissionApplication.class, args);
    }

    @Bean
    public CacheKeyGenerator cacheKeyGenerator(){
        return new LockKeyGenerator();
    }

}
