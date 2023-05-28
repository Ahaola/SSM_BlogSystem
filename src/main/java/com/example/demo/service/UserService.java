package com.example.demo.service;

import com.example.demo.entity.Userinfo;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public int reg(Userinfo userinfo){
        return userMapper.reg(userinfo);
    }

    public Userinfo getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    public Userinfo getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
