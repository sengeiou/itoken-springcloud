package com.funtl.itoken.common.web.service;

import com.funtl.itoken.common.hystrix.Fallback;

/**
 * 通用服务消费者接口
 *
 * @ClassName BaseClientService
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/10 10:56
 * @Version 1.0
 **/
public interface BaseClientService {
    default String page(int pageNum, int pageSize, String domainJson) {
        return Fallback.badGateway();
    }
}
