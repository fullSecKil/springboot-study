package com.example.rabbitmq_delay;

import com.example.rabbitmq_delay.controller.BookController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqDelayApplicationTests {
    @Autowired
    BookController bookController;
    @Test
    public void contextLoads() {
        bookController.defaultMessage();
    }

}
