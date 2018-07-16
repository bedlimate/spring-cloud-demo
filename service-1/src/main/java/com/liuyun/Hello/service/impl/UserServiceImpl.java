package com.liuyun.Hello.service.impl;

import com.liuyun.Hello.service.IUserService;
import com.liuyun.common.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Override
    public void saveUser(User user) {
        System.out.println("save user:[ " + user + "] to db succeed!" );
    }
}
