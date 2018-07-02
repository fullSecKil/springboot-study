package com.example.mapper_paging.dao;

import com.example.mapper_paging.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名统计
     * @param username
     * @return
     */
    @Select("select count(1) from t_user where username = #{username}")
    int countByUsername(String username);
}
