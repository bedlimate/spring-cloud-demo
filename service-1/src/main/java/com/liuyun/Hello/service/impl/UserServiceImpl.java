package com.liuyun.Hello.service.impl;

import com.liuyun.Hello.mapper.UserMapper;
import com.liuyun.Hello.service.IUserService;
import com.liuyun.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }
}
