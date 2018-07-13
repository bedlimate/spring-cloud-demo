package com.liuyun.Hello.controller;

import com.liuyun.Hello.service.IUserService;
import com.liuyun.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PutMapping("/user")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }
}
