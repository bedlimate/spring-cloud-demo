package com.liuyun.springcloudhystrix.controller;

import com.liuyun.springcloudhystrix.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Qualifier("helloServiceWithHystrix")
    @Autowired
    IHelloService helloService;

    @GetMapping("/hello")
    public String sayHello(@RequestParam String name){
        return helloService.sayHello(name);
    }
}
