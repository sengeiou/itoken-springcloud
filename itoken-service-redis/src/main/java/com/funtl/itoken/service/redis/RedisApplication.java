package com.funtl.itoken.service.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName RedisApplication
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/4 11:36
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
