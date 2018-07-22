package com.liuyun.Hello.controller;

import com.liuyun.Hello.service.IUserService;
import com.liuyun.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PutMapping("/user")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    // GET http://localhost:8011/users?userId=100,200,300
    @GetMapping("/users")
    public List<User> getUsers(@RequestParam List<Integer> userIds){
        return userService.getUsers(userIds);
    }
}
