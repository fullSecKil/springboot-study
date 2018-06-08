package com.example.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息产生者
 */
@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String context = "hi, fanout msg";
        System.out.println("Sender : "+ context);
        this.rabbitTemplate.convertAndSend("fanoutExchange","",context);
    }
}
