package com.funtl.itoken.web.backend.service.fallback;

import com.funtl.itoken.web.backend.service.RedisService;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisServiceFallback
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/4 16:04
 * @Version 1.0
 **/
@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public String put(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}
