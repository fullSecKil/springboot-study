package com.example.demo;

import com.example.interceptor.MyInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.security.auth.login.AppConfigurationEntry;
import java.util.Arrays;

@SpringBootApplication
@ComponentScan("com.example")
@MapperScan(basePackages = "com.example.mybatis.mapper")
//@ServletComponentScan(basePackages = "com.example..servlet")

public class DemoApplication  extends WebMvcConfigurerAdapter{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor()).addPathPatterns("/*");
    }
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        SpringApplication.run(DemoApplication.class, args);
        /*SpringApplication application = new SpringApplication(DemoApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);*/
        /*new SpringApplicationBuilder(DemoApplication.class).bannerMode(Banner.Mode.OFF).run(args);*/
    }
}
