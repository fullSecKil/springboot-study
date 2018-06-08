package com.example.rabbitmq_queue.controller;

import com.example.rabbitmq_queue.config.RabbitConfig;
import com.example.rabbitmq_queue.pojo.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public BookController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public void defaultMessage(){
        Book book = new Book("1","RebbitMq");
        rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_BOOK_QUEUE, book);       //转发
        rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_BOOK_QUEUE,book);
    }
}
