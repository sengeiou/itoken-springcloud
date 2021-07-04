package com.funtl.itoken.service.redis.service.impl;

import com.funtl.itoken.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisServiceImpl
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/4 11:58
 * @Version 1.0
 **/
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void put(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
