package com.liuyun.ribbon.service.impl;

import com.liuyun.ribbon.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloServiceImpl implements IHelloService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String sayHello(String name) {
        return restTemplate.getForObject("http://SERVICE/hi?name=" + name, String.class);
    }
}
