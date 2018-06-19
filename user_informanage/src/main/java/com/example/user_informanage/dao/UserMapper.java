package com.example.user_informanage.dao;

import com.example.user_informanage.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where age=#{username}")
    public User getUser(String username);

    @Insert("insert into user values(null,#{username},#{password},#{realname},#{email},#{photo},#{title})")
    public boolean saveUser(User user);
}
