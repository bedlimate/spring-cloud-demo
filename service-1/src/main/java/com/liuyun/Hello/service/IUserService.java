package com.liuyun.Hello.service;


import com.liuyun.common.entity.User;

import java.util.List;

public interface IUserService {

    void saveUser(User user);

    List<User> getUsers(List<Integer> userIds);
}
