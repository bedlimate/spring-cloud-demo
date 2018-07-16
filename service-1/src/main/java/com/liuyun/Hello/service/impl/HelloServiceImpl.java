package com.liuyun.Hello.service.impl;

import com.liuyun.Hello.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements IHelloService {

    @Value("${server.port}")
    private Integer port;

    @Override
    public String sayHello(String name) {
        return "Hello " + name + ", I'm from " + port +"!";
    }
}
