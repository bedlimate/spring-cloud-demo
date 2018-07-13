package com.liuyun.feign.controller;


import com.liuyun.common.entity.User;
import com.liuyun.feign.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PutMapping("/user")
    public void saveUser(){
        User user = new User();
        user.setId(1);
        user.setName("Tom");
        user.setBirthday(LocalDate.now());
        user.setNow(OffsetDateTime.now());
        userService.saveUser(user);
    }
}
