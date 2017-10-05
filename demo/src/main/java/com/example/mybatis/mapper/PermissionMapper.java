package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {

    @Select("select * from user")
    public List<Permission> selectAll();

    @Insert("insert into user values(#{id},#{username},#{age},#{password},#{image},#{isactivity})")
    @Options(keyProperty="id",keyColumn="id",useGeneratedKeys=true)         //设置自增
    public void insert(Permission permission);
}
