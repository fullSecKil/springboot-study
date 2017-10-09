package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE nickname = #{nickname}")
    List<User> findByUsername(@Param("nickname") String nickname);

    @Update("UPDATE user SET password='xueruizuishuai' WHERE id=#{id}")
    boolean updateUser(@Param("id") Integer id);

    int insert(User user);
}
