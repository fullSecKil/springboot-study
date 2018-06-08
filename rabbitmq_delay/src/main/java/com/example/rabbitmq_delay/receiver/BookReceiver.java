package com.example.rabbitmq_delay.receiver;

import com.example.rabbitmq_delay.config.RabbitConfig;
import com.example.rabbitmq_delay.pojo.Book;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * BOOK_QUEUE消费者
 */
@Component
public class BookReceiver {
    private final static Logger log = LoggerFactory.getLogger(BookReceiver.class);

    @RabbitListener(queues = {RabbitConfig.REGISTER_QUEUE_NAME})
    public void listenerDelayQueue(Book book, Message message, Channel channel){
        log.info("[listenerDelayQueue 监听的消息] - [消费时间] -[{}]-[{}]",book.toString(),LocalDateTime.now());
        try{
            //TODO 通知MQ消息消费成功，ACK
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
