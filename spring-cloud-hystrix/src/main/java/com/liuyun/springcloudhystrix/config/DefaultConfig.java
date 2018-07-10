package com.liuyun.springcloudhystrix.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DefaultConfig {

    @Bean
    @LoadBalanced
    public RestTemplate newRestTemplate(){
        return new RestTemplate();
    }
}
