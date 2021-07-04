package com.funtl.itoken.service.upload.fastdfs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java 配置方式定义 StorageFactory 的 Bean 使其可以被依赖注入
 *
 * @ClassName FastDFSConfiguration
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/11 11:36
 * @Version 1.0
 **/
@Configuration
public class FastDFSConfiguration {
    @Bean
    public StorageFactory storageFactory() {
        return new StorageFactory();
    }
}
