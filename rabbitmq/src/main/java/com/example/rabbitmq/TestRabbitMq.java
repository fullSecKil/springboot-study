package com.example.rabbitmq;

import com.example.rabbitmq.controller.FanoutSender;
import com.example.rabbitmq.controller.HelloSender;
import com.example.rabbitmq.controller.NeoSender;
import com.example.rabbitmq.controller.TopicSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRabbitMq {
    @Autowired
    private HelloSender helloSender;

    @Autowired
    private NeoSender neoSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private TopicSend topicSend;
    @Test
    public void hello() throws Exception {
      /*  helloSender.send();
        for(int i=1; i<100; i++){
            neoSender.send(i);
        }*/
        //fanoutSender.send();
        topicSend.send2();
        //topicSend.send1();
    }
}
