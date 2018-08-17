package com.example.userinformanage.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *  RabbitMQ配置，新建队列，路由
 *
 * @author Dusk
 * @since 2018/7/10
 */
@Configuration
public class RabbitConfig {

    public static final String DEFAULT_MAIL_QUEUE = "dev.mail.register.default.queue";
    public static final String MANUAL_MAIL_QUEUE = "dev.mail.register.manual.queue";
    public static final String DEFAULT_MAIL_EXCHANGE= "dev.mail.register.default.exchange";

    @Bean
    public Queue defaultMailQueue(){
        // QUEUE 的名字,持久化
        return new Queue(DEFAULT_MAIL_QUEUE,true);
    }

    @Bean
    public Queue manualMailQueue(){
        return new Queue(MANUAL_MAIL_QUEUE,true);
    }

    // 交换机
    @Bean
    TopicExchange mailExchange(){
        return new TopicExchange(DEFAULT_MAIL_EXCHANGE);
    }

    // 绑定交换机，实现路由
    @Bean
    public Binding bindingExchangMessage(Queue defaultMailQueue, TopicExchange mailExchange){
        return BindingBuilder.bind(defaultMailQueue).to(mailExchange).with("mail.register");
    }
}
