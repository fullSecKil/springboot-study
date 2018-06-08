package com.example.rabbitmq_delay.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * RabbitMQ配置
 */

@Configuration
public class RabbitConfig {

    private static final Logger log = LoggerFactory.getLogger(RabbitConfig.class);

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {   //Caching Connection Factory  高速缓存连接工厂
        cachingConnectionFactory.setPublisherConfirms(true);        //设置发布确认
        cachingConnectionFactory.setPublisherReturns(true);           //设置发布返回
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMandatory(true);      //强制
        //设置确认回传
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功：correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        //设置回调
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));

        return rabbitTemplate;
    }

    /**
     * 延迟队列TTL名称
     */
    private static final String REGISTER_DELAY_QUEUE = "dev.book.register.delay.queue";

    /**
     * DLX,dead Letter发送到的 exchange
     * TODO 此处的exchange，消息发送到死信交换机
     */
    public static final String REGISTER_DELAY_EXCHANGE = "dev.book.register.delay.exchange";

    /**
     * routing key          消息与一个特定的路由键完全匹配
     * TODO 此处的 routingKey 很重要要,具体消息发送在该 routingKey 的
     */
    public static final String DELAY_ROUTING_KEY = "all";

    public static final String REGISTER_QUEUE_NAME = "dev.book.register.queue";
    public static final String REGISTER_EXCHANGE_NAME = "dev.book.register.exchange";
    public static final String ROUTING_KEY = "all";

    /**
     * 延迟队列配置
     * <p>
     * 1、params.put("x-message-ttl", 5*1000)
     * TODO 第一种方式是直接设置Queue延迟时间 但如果直接给队列设置过期时间，这种方法不是很灵活
     * 2、rabbitTemplate.convertAndSend(book, message -> {
     * message.getMessageProperties().setExpiration(2 * 1000 + "");
     * return message;
     * });
     * </p>
     */
    @Bean
    public Queue delayProcessQueue() {
        Map<String, Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange", REGISTER_EXCHANGE_NAME);
        params.put("x-dead-letter-routing-key", ROUTING_KEY);
        return new Queue(REGISTER_DELAY_QUEUE, true, false, false, params);
    }

    //新建交换机
    @Bean
    public DirectExchange delayExchange(){          //直接交换机
        return new DirectExchange(REGISTER_DELAY_EXCHANGE);
    }

    /**
     * 需要将队列绑定到交换机上，要求该消息与特定的路由键完全匹配
     *  这是一个完整的匹配，如果一个队列绑定到该交换机上要求路由键“dog”,则只有被标记为“dog"的消息才被转发，不会转发dog.puppy，也不会转发dog.guard，只会转发dog。
     * TODO 它不像 TopicExchange 那样可以使用通配符适配多个
     */

    //TTL队列绑定到交换机
    @Bean
    public Binding dlxBinding(Queue delayProcessQueue, DirectExchange delayExchange){
        return BindingBuilder.bind(delayProcessQueue).to(delayExchange).with(ROUTING_KEY);
    }

    @Bean
    public Queue registerBookQueue(){
        return new Queue(REGISTER_QUEUE_NAME);
    }

    @Bean
    public TopicExchange registerBookTopicExchange(){           //标题路由器
        return new TopicExchange(REGISTER_EXCHANGE_NAME);
    }

    /**
     *将路由键和模式进行匹配，此时队列需要绑定要一个模式上。
     * 号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。因此“audit.#”能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”
     */
    @Bean
    public Binding registerBookBinding(Queue registerBookQueue, TopicExchange registerBookTopicExchange){
        return BindingBuilder.bind(registerBookQueue).to(registerBookTopicExchange).with(ROUTING_KEY);
    }

}