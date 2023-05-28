package com.example.demo.mapper;

import com.example.demo.entity.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    // 注册
    int reg(Userinfo userinfo);
    // 根据用户查询 userinfo 对象
    Userinfo getUserByName(@Param("username") String username);

    Userinfo getUserById(@Param("id") Integer id);
}
