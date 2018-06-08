package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue queue(){
        return new Queue("hello");
    }

    @Bean Queue queue2(){
        return new Queue("neo");
    }

    @Bean
    Queue AMessage(){
        return new Queue("fanout.A");
    }

    @Bean
    Queue BMessage(){
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }

    @Bean
    FanoutExchange fanoutExchange(){        //FanoutExchange 新建一个交换机
        return new FanoutExchange("fanoutExchange");
    }

    /**
     *
      * @return 队列绑定到交换机
     */
    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }

    final static String message = "topic.A";
    final static String messages= "topic.B";

    @Bean
    public Queue queueMessage(){
        return new Queue(message);
    }

    @Bean
    public Queue queueMessages(){
        return new Queue(messages);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangMessages(Queue queueMessages, TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
