package com.liuyun.springcloudhystrix.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DefaultConfig {

    //此处必须加上@LoadBalanced这个注解，因为在RestTemplate调用的时候，
    // 为了不具体依赖于某一服务，使用了以服务名称代替服务的URL，如果不加上这个注解，后续在发起真实http请求的时候
    // 无法将服务名称替换为真实的服务URL，从而会跑出无效的URL异常

    @Bean
    @LoadBalanced
    public RestTemplate newRestTemplate(){
        return new RestTemplate();
    }
}
