package com.liuyun.springcloudhystrix.controller;

import com.liuyun.common.entity.User;
import com.liuyun.springcloudhystrix.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/users")
    public List<User>  getUsers(@RequestParam List<Integer> userIds){
        return userService.getUsers(userIds);
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable Integer userId) throws ExecutionException, InterruptedException {
        return userService.getUserById(userId);
    }

}
