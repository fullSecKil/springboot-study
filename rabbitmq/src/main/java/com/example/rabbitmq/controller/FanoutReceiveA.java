package com.example.rabbitmq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.A")
public class FanoutReceiveA {
    @RabbitHandler
    public void process(String message){
        System.out.println("fanout Receiver A :"+message);
    }
}

@Component
@RabbitListener(queues = "fanout.B")
class FanoutReceiveB {
    @RabbitHandler
    public void process(String message){
        System.out.println("fanout Receiver B :"+message);
    }
}

@Component
@RabbitListener(queues = "fanout.C")
class FanoutReceiveC {
    @RabbitHandler
    public void process(String message){
        System.out.println("fanout Receiver C :"+message);
    }
}
