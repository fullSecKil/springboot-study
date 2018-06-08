package com.example.rabbitmq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "neo")
public class NeoReceiver1 {
    @RabbitHandler                  //处理程序
    public void process(String neo){
        System.out.println("Receiver 1:"+neo);
    }
}

@Component
@RabbitListener(queues = "neo")
class NeoReceiver2 {
    @RabbitHandler                  //处理程序
    public void process(String neo){
        System.out.println("Receiver 2:"+neo);
    }
}
