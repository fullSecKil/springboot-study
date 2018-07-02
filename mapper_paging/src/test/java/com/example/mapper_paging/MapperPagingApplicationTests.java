package com.example.mapper_paging;

import com.example.mapper_paging.dao.UserMapper;
import com.example.mapper_paging.pojo.User;
import com.example.mapper_paging.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperPagingApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(MapperPagingApplicationTests.class);

    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {
        final User user1 = new User("u1", "p1", 2.1);
        final User user2 = new User("u2", "p2", 2.2);
        final User user3 = new User("u3", "p3", 2.3);

      /*  userService.insertSelective(user1);
        log.info("[user1回写主键] - [{}]", user1.getId());
        userService.insertSelective(user2);
        log.info("[user2回写主键] - [{}]", user2.getId());
        userService.insertSelective(user3);
        log.info("[user3回写主键] - [{}]", user3.getId());
        final int count = userService.countByUsername("u1");
        log.info("[调用自己写的SQL] - [{}]", count);


        userService.insertSelective(new User("u1", "p1", 2.1));
        userService.insertSelective(new User("u1", "p1", 2.1));
        userService.insertSelective(new User("u1", "p1", 2.1));
        userService.insertSelective(new User("u1", "p1", 2.1));
        userService.insertSelective(new User("u1", "p1", 2.1));
        userService.insertSelective(new User("u1", "p1", 2.1));
        userService.insertSelective(new User("u1", "p1", 2.1));
        userService.insertSelective(new User("u1", "p1", 2.1));
        userService.insertSelective(new User("u1", "p1", 2.1));*/


        // TODO 分页加排序
        final PageInfo<Object> pageInfo = PageHelper.startPage(2,10) // 第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
                    .setOrderBy("id desc").doSelectPageInfo(userService::selectAll);
        log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());


          PageHelper.startPage(1, 10).setOrderBy("id desc");
        final PageInfo<User> userPageInfo = new PageInfo<>(this.userService.selectAll());
        log.info("[普通写法] - [{}]", userPageInfo);

    }

}
