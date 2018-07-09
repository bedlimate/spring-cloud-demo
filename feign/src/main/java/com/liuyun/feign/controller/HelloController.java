package com.liuyun.feign.controller;

import com.liuyun.feign.api.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    IHelloService helloService;

    @RequestMapping("/hi")
    public String sayHello(@RequestParam String name){
        return helloService.sayHello(name);
    }
}
