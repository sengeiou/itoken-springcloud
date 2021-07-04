package com.funtl.itoken.service.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName ServiceUploadApplication
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/11 11:22
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class ServiceUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUploadApplication.class, args);
    }
}
