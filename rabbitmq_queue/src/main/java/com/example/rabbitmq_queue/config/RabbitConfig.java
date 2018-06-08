package com.example.rabbitmq_queue.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    public static final String DEFAULT_BOOK_QUEUE = "dev.book.register.default.queue";
    public static final String MANUAL_BOOK_QUEUE = "dev.book.register.manual.queue";

    @Bean
    public Queue defaultBookQueue(){
        //第一个是队列名，第二个是否需要持久化
        return new Queue(DEFAULT_BOOK_QUEUE,true);
    }

    @Bean
    public Queue manualBookQueue(){
        return new Queue(MANUAL_BOOK_QUEUE,true);
    }
}
