package com.liuyun.springcloudhystrix.service.base.impl;

import com.liuyun.springcloudhystrix.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("baseHelloService")
public class HelloServiceImpl implements IHelloService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String sayHello(String name) {
        return restTemplate.getForObject("http://SERVICE-1/hi?name=" + name, String.class);
    }
}
