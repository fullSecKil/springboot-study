package com.example.userinformanage;

import com.example.userinformanage.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInformanageApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    // 从properties中取发送者mail
    @Value(("${spring.mail.username}"))
    private String provider;

    final static private Logger log = LoggerFactory.getLogger(UserInformanageApplicationTests.class);

    @Test
    public void contextLoads() throws MessagingException {
        // userService.addUser(new User("li", "1231", "en2213", "x", "xxx"));

        //System.out.println(userService.getUser("dusk"));

        //System.out.println(userService.selectAll());

        //log.info("当前时间：{}", LocalDateTime.now().withNano(0).toString());
        //log.info("user: {}", userService.selectAll());

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        Context cont = new Context();

        Map<String, Object> context = new HashMap<>();
        context.put("username", "en2213");
        context.put("realname",  "dusk");
        context.put("password", "123456");
        context.put("time", LocalDateTime.now().withNano(0).toString());

        //cont.setVariable("message", "当前时间："+LocalDateTime.now().withNano(0).toString());

        cont.setVariables(context);

        String process = templateEngine.process("email", cont);

        helper.setFrom(provider);
        helper.setTo("en2213a@163.com");
        helper.setSubject("SpringBootThymeleaf模板邮件");
        helper.setText(process, true);

        mailSender.send(mimeMessage);

    }

}
