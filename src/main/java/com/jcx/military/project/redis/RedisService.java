package com.jcx.military.project.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {


    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 位图操作
     * @param key
     * @param index
     * @param value
     * @return
     */
    public Boolean setBitMap(String key, int index, boolean value) {
        return redisTemplate.opsForValue().setBit(key,index,value);
    }


    /**
     * 位图操作
     * @param key
     * @return
     */
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }



    public boolean getLock(String key) {
        return true;
    }

    public boolean releaseLock(String key) {
        return true;
    }



}
