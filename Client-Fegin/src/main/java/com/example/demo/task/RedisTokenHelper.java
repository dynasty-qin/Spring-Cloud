package com.example.demo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.concurrent.TimeUnit;

/**
 * @Author : Harry
 * @Date :  2018-05-30 10:25
 * @Description : Redis token 工具
 */
@Repository
public class RedisTokenHelper {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    /**
     * 键值对存储 字符串 ：有效时间3分钟
     * @param tokenType Token的key
     * @param Token Token的值
     */
    public void save(String tokenType,String Token){
        stringRedisTemplate.opsForValue().set(tokenType, Token, 180, TimeUnit.SECONDS);
    }
    /**
     * 根据key从redis获取value
     * @param tokenType
     * @return String
     */
    public String getToken(String tokenType){
        return stringRedisTemplate.opsForValue().get(tokenType);
    }
    /**
     * redis 存储一个对象
     * @param key
     * @param obj
     * @param timeout 过期时间  单位：s
     */
    public void saveObject(String key,Object obj,long timeout){
        redisTemplate.opsForValue().set(key, obj,timeout,TimeUnit.SECONDS);
    }
    /**
     * redis 存储一个对象  ,不过期
     * @param key
     * @param obj
     */
    public void saveObject(String key,Object obj){
        redisTemplate.opsForValue().set(key, obj);
    }
    /**
     * 从redis取出一个对象
     * @param key
     */
    public Object getObject(String key){
        return redisTemplate.opsForValue().get(key);
    }
    /**
     * 根据Key删除Object
     * @param key
     */
    public void removeObject(String key){
        redisTemplate.delete(key);
    }
}
