package com.example.userinformanage.help.mailsend;

import com.example.userinformanage.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

/**
 *  Email的消费者,实现发送
 * @author Dusk
 */
@Component
public class MailReceiver {

    @Autowired
    SendMail sendMaill;

    @RabbitListener(queues = RabbitConfig.DEFAULT_MAIL_QUEUE)
    public void process(Map mailcontext, Message message, Channel channel) {

        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try{
            sendMaill.sendRegisterEmail(mailcontext);
            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // TODO 处理失败,重新压入MQ
                channel.basicRecover();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
