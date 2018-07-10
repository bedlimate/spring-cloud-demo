package com.liuyun.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DefaultCofiguration {

    /**
     * 对于服务消费者而言，只要通过该RestTemplate进行http调用，都会默认进行负载均衡
     * 因为该实例在此处被{@link org.springframework.cloud.client.loadbalancer.LoadBalanced}修饰
     * 这是典型的在客户端进行负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate newRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new Netty4ClientHttpRequestFactory());
        return restTemplate;
    }

}
