package com.example.redisdemo;

import com.example.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Chapter8ApplicationTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;


    @Test
    public void test(){

        stringRedisTemplate.delete(Arrays.asList("k1","battcn:user:1","k2"));
        //redisTemplate.delete("battcn:list:1");
        //执行服务线程池   线程数
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0,1000).forEach(i->executorService.execute(()->stringRedisTemplate.opsForValue().increment("kk",1)));   //increment增量

        //stringRedisTemplate.opsForValue().set("k2","v2");
        //stringRedisTemplate.opsForValue().set("k1","v1");
        stringRedisTemplate.opsForValue().increment("kv",55);
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        final String k2 = stringRedisTemplate.opsForValue().get("k2");
        final String kk = stringRedisTemplate.opsForValue().get("kk");
        final String kv = stringRedisTemplate.opsForValue().get("kv");
        System.out.println("[字符k1缓存结果] - [{}]"+k1);
        System.out.println("[字符k2缓存结果] - [{}]"+k2);
        System.out.println("[字符kk缓存结果] - [{}]"+kk);
        System.out.println("[字符kv缓存结果] - [{}]"+kv);

        String key = "battcn:user:1";
        //redisTemplate.opsForValue().set(key, new User("u1", "pa",123));
        // TODO 对应 String（字符串）
        final User user = (User) redisTemplate.opsForValue().get(key);
        System.out.println("[对象缓存结果] - [{"+user+"}]");


/*       String key2 = "battcn:list:1";
        stringRedisTemplate.opsForList().set(key2, Arrays.asList("wo", "zhui", "shuai"));
        System.out.println(redisTemplate.opsForValue().get(key2));*/
    }
}
