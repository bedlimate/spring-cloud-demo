package com.liuyun.springcloudhystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringCloudHystrixApplication {



    public static void main(String[] args) {
        SpringApplication.run(SpringCloudHystrixApplication.class, args);
    }
}
