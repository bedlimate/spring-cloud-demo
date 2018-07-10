package com.liuyun.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-1", fallback = IHelloService.HelloServiceFallback.class)
public interface IHelloService {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHello(@RequestParam("name") String name);

    @Component
    class HelloServiceFallback implements IHelloService{

        @Override
        public String sayHello(String name) {
            return "This is an error from fallback in feign client!";
        }
    }
}
