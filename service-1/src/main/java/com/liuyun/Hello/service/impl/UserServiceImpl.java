package com.liuyun.Hello.service.impl;

import com.liuyun.Hello.mapper.UserMapper;
import com.liuyun.Hello.service.IUserService;
import com.liuyun.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public List<User> getUsers(List<Integer> userIds) {
        userIds = Optional.ofNullable(userIds)
                .orElseThrow(() -> new IllegalArgumentException("userIds is null"))
                .stream().filter(userId -> userId != null)
                .collect(Collectors.toList());


        return userMapper.getUsers(userIds);
    }

}
