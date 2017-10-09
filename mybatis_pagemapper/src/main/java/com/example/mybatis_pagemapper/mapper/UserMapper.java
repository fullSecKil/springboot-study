package com.example.mybatis_pagemapper.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
/*
    @Select("select * from t_user where username=#{username}")
    List<User> countByUsername(@Param("username") String username);

    @Insert("insert into t_user(`username`,`password`)values(#{username},#{password})")
    boolean addUser(@Param("username") String username, @Param("password") String password);

    @Select("select * from t_user ")
    List<User> selectA();*/
}
