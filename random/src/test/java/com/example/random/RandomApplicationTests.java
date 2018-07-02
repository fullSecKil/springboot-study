package com.example.random;

import com.example.random.blog.BlogProperties;
import com.example.random.blog.HomeProperties;
import com.example.random.controller.UserController;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
/**
 * 单元测试
 * mock mvc模拟生成器
 */
public class RandomApplicationTests {

    private MockMvc mvc;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    private static final Logger log =  LoggerFactory.getLogger(RandomApplicationTests.class);

    @Autowired
    private BlogProperties blogProperties;

    @Autowired
    private HomeProperties homeProperties;

    @Test
    public void contextLoads() throws Exception {
       /* Assert.assertEquals(blogProperties.getName(),"xuerui");
        Assert.assertEquals(blogProperties.getTitle(),"zuishuai");
        System.out.println(blogProperties.getDesc());

        //log.info("随机字符串 {[]}", blogProperties.getValue());
        log.info("随机int {}", blogProperties.getNumber());
        log.info("随机long {}", blogProperties.getBignumber());
        log.info("10以内的随机数 {}", blogProperties.getTest1());
        log.info("10到20的随机数 {}", blogProperties.getTest2());
        //log.info(" {[]}", );

        log.info("家乡 {}", homeProperties);*/
        //  测试UserController
        RequestBuilder request = null;

        request = get("/users/");

        mvc.perform(request)
                        .andExpect(status().isOk())
                        .andExpect(content().string(IsEqual.equalTo("[]")));

        // 2、post提交一个user
        request = post("/users/")
                .param("id", "1")
                .param("name", "测试大师")
                .param("age", "20");
        mvc.perform(request)
                .andExpect(content().string(IsEqual.equalTo("success")));

        // 3、 get获取user列表，应该有刚才插入的数据
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));

        // 4、 put修改id为1的user
        request = put("/users/1")
                .param("name", "薛瑞")
                .param("age", "22");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 5、get访问id为1的user
        request = get("/users/1");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"薛瑞\",\"age\":22}")));

        // 6、 del删除id为1的user
        request = delete("/users/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 7、 get查询users列表
        request = get("/users");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }

}
