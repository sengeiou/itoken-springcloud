package com.funtl.itoken.service.redis.service;

/**
 * @ClassName RedisService
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/4 11:52
 * @Version 1.0
 **/
public interface RedisService {

    /**
     *
     * @param key
     * @param value
     * @param seconds 超时时间
     */
    public void put(String key, Object value, long seconds);

    public Object get(String key);
}
