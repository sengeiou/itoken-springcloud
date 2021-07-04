package com.funtl.itoken.service.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName ServicePostsApplication
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/8 17:24
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = "com.funtl.itoken")
@EnableEurekaClient
@MapperScan(basePackages = {"com.funtl.itoken.common.mapper","com.funtl.itoken.service.posts.mapper"})
@EnableSwagger2
public class ServicePostsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePostsApplication.class, args);
    }
}
