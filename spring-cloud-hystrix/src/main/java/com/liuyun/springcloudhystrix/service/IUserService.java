package com.liuyun.springcloudhystrix.service;


import com.liuyun.common.entity.User;

import java.util.List;



public interface IUserService {

    User getUserById(Integer userId);

    List<User> getUsers(List<Integer> userIds);
}
