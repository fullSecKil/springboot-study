package com.example.userinformanage.help.mailsend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * @Title 注册返回邮件
 *
 * @Description: 发送thymeleaf email模板文件
 * @author Dusk
 */
@Component
public class SendMail {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    // 从properties中取发送者mail
    @Value(("${spring.mail.username}"))
    private String provider;

    public void sendRegisterEmail(Map context) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        Context emailContext = new Context();
        emailContext.setVariables(context);
        String process = templateEngine.process("email", emailContext);

        // 发送者
        helper.setFrom(provider);
        // 接收者
        helper.setTo(context.get("receiver").toString());
        // 邮件主题
        helper.setSubject("尊敬的："+context.get("realname")+"您好！恭喜您注册成功");
        // 邮件内容
        helper.setText(process, true);
        // 发送邮件
        mailSender.send(mimeMessage);

    }
}
