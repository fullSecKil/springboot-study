package com.example.javamailsender;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * springboot实现发送邮件，主要借鉴了csdn博客上的例子（https://blog.csdn.net/gebitan505/article/details/54945440）
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavamailsenderApplicationTests {
    @Autowired
    private JavaMailSender mailSender;          //邮件发送器

    @Test
    public void sendSimpleEmail() {         //使用SimpleMailMessage实现了简单的邮件发送

        SimpleMailMessage message = new SimpleMailMessage();        //简单邮件消息
        message.setFrom("en2213@163.com");      //发送者
        message.setTo("en2213a@163.com");     //接收者
        message.setSubject("主题：简单邮件");      //邮件主题
        message.setText("测试邮件内容");                  //邮件内容

        mailSender.send(message);               //发送邮件
    }

    /**
     *  我们通过使用SimpleMailMessage实现了简单的邮件发送，但是实际使用过程中
     *  ，我们还可能会带上附件、或是使用邮件模块等。这个时候我们就需要使用MimeMessage来设置复杂一些的邮件内容
     */

    /**
     * 测试发送附件(图片)
     * @throws MessagingException
     */

    @Test
    public void sendAttachmentsEmail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("en2213@163.com");      //发送者
        helper.setTo("en2213a@163.com");     //接收者
        helper.setSubject("主题：简单邮件");      //邮件主题
        helper.setText("测试邮件内容");                  //邮件内容

        //附件1,获取文件对象.
        FileSystemResource file1 = new FileSystemResource(new File("E:\\download\\test.png"));
        //添加附件，这里第一个参数是邮件中显示的名称，一定要有文件后缀，不然就无法显示图片
        helper.addAttachment("头像1.png", file1);

        mailSender.send(mimeMessage);
    }

    /**
     * 邮件中使用静态资源
     * @throws Exception
     */
    @Test
    public void sendInlineMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //基本设置
        helper.setFrom("en2213@163.com ");  //发送者
        helper.setTo("en2213a@163.com");    //接受者

        helper.setSubject("测试静态资源(邮件主题)");  //邮件主题

        //说明：嵌入图片<img src='cid:head'/>，其中cid:是固定的写法，而aaa是一个contentId。
        helper.setText("<body>这是图片：<img src='http://img.zcool.cn/community/0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png'/></body>", true);

        /*FileSystemResource file = new FileSystemResource(new File("E:\\download\\test.png"));
        helper.addInline("head",file);*/

        mailSender.send(mimeMessage);
    }

    /**
     * 模板邮件
     * @throws  Exception
     */

    @Test
    public void sendTemplateMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        // 基本设置
        helper.setFrom("en2213@163.com");   //发送者
        helper.setTo("en2213a@163.com");    //接受者
        helper.setSubject("模板邮件（邮件主题）");    //邮件主题.

        Map<String, Object> model = new HashMap<>();
        model.put("username", "薛瑞");

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

        // 设置哪里读取ftl模板
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");

        // 在模本文件目录中寻找名称为name的模板文件
        Template template = cfg.getTemplate("email.ftl");

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(html, true);

        mailSender.send(mimeMessage);
    }
}
