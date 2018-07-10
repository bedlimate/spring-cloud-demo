package com.liuyun.springcloudhystrix.service;

import com.liuyun.springcloudhystrix.hystrix.HelloServiceCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("helloServiceWithHystrix")
public class HelloServiceWithHystrix implements IHelloService {

    @Qualifier("baseHelloService")
    @Autowired
    private IHelloService delagate;

    @Override
    public String sayHello(String name) {
        HelloServiceCommand command = new HelloServiceCommand(delagate, name);
        return command.execute();
    }
}
