package com.example.rabbitmq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.A")
public class TopicReceiver {
    @RabbitHandler
    public void process(String message){
        System.out.println("Topic Receiver1 :"+message);
    }
}

@Component
@RabbitListener(queues = "topic.B")
class TopicReceiver2 {
    @RabbitHandler
    public void process(String message){
        System.out.println("Topic Receiver2 :"+message);
    }
}
