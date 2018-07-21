package com.liuyun.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigClientApplication {

    /*
        作为分布式配置中心的客户端的配置文件，文件名称一定要是 “bootstrap.yaml” 或者 “bootstrap.properties”
        只有这样，才可以才控制台看到如下日志输出：
            “Fetching config from server at : http://localhost:9000”
        如果配置文件名称叫做 “bootstrap.yaml”，
        那么设定的 spring.cloud.config.uri将不会起作用
        否则，看到的日志将是这样的：
            “Fetching config from server at : http://localhost:8888”(使用默认的设置)


       要在客户点启用配置刷新功能需要注意点：
            1. 在当前版本下， 首先需要做如下配置
            management:
                  endpoints:
                    web:
                      exposure:
                        include: "*" （如果不做这样配置， 会得到404）
            2. 配置刷新的时候请求的URL应该的格式：
                    POST http://{host}/actuator/refresh
            3. 请求头配置
                Content-Type： application/json (如果携带请求头不是这样， 会得到415)
     */

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
