package com.forezp.servicezuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *  Zuul主要实现路由转发和过滤，zuul默认和Ribbon结合实现了负载均衡的功能。
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication

public class ServiceZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceZuulApplication.class, args);
    }
}
