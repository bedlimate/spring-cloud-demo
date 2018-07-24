package com.liuyun.Hello.service.impl;

import com.liuyun.Hello.mapper.UserMapper;
import com.liuyun.Hello.service.IUserService;
import com.liuyun.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }


    @Override
    public User getUserById(Integer userId) {
        log.info("userId: [{}]", userId);
        Optional.ofNullable(userId).orElseThrow(() ->
                new IllegalArgumentException("userId is null"));
        return userMapper.getUserById(userId);
    }

    @Override
    public List<User> getUsers(List<Integer> userIds) {
        log.info("userIds: [{}]", userIds);
        userIds = Optional.ofNullable(userIds)
                .orElseThrow(() -> new IllegalArgumentException("userIds is null"))
                .stream().filter(userId -> userId != null)
                .collect(Collectors.toList());
        List<User> users = userMapper.getUsers(userIds);
        users.stream().noneMatch()
        log.info("user count: [{}]", users.size());
        return users;
    }

}
