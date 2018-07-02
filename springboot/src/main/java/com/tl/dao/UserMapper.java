package com.tl.dao;

import com.tl.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     *getUser
     *
     * @param age
     * @return List<User>
     */
    @Select("select id,username,age,password,image,isactivity from user where age=#{age}")
    public List<User> getUser(String age);
}
