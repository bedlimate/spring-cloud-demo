package com.liuyun.cloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class CloudConfigApplication {

    /*
    通过是配置中心，可以解决痛点：
        1. 同一组服务依赖于各自的配置文件，如果需要改动的时候，需要对每个实例的配置文件逐一更新。
        2. 代码与配置分离
    spring-cloud的配置中心，也需要借助远程仓库（git或者svn）

    config-server: 统一管理配置文件的一个server，需要定义远程仓库的位置（uri），用户名(username)，密码(password)等




     */


    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication.class, args);
    }
}
