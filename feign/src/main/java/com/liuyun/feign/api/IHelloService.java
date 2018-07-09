package com.liuyun.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-1")
public interface IHelloService {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHello(@RequestParam("name") String name);
}
