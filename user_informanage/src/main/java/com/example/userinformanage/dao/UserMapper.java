package com.example.userinformanage.dao;

import com.example.userinformanage.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.BaseMapper;

/**
 *  @author Dusk
 */
@Mapper
@Transactional(rollbackFor = Exception.class)
public interface UserMapper  extends BaseMapper<User>{

    @Select("select * from user where username=#{username}")
    public User getUser(String username);

    @Select("select * from user where username=#{username} and password=#{password}")
    public User selectUserNameAndPassword(String username, String password);
}
