package com.liuyun.feign.api;

import com.liuyun.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-1")
public interface IUserService {

    @PutMapping("/user")
    void saveUser(@RequestBody User user);
}
