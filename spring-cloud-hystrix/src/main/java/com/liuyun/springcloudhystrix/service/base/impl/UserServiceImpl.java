package com.liuyun.springcloudhystrix.service.base.impl;

import com.liuyun.common.entity.User;
import com.liuyun.springcloudhystrix.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    RestTemplate restTemplate;


    @Override
    public User  getUserById(Integer userId) {
        return restTemplate.getForObject("http://SERVICE-1/user/" + userId, User.class);
    }

    @Override
    public List<User> getUsers(List<Integer> userIds) {
        System.out.println("userIds: " + userIds);
        StringBuilder sb = new StringBuilder();
        for(Integer userId : userIds){
            sb.append(userId);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return restTemplate.exchange("http://SERVICE-1/users?userIds=" + sb.toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {}
        ).getBody();
    }
}
