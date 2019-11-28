package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.UserMapper;
import com.cn.phoenix.api.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User findByUsername(User user) {
        return userMapper.findByUsername(user.getUsername());
    }

    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }

    public User findUserByUsername(String username){
        return userMapper.findUserByUsername(username);

    }

}
