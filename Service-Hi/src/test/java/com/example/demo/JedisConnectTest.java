package com.example.demo;

import redis.clients.jedis.Jedis;

/**
 * @Author : Harry
 * @Date :  2018-05-22 09:37
 * @Description :
 */
public class JedisConnectTest {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("47.104.240.128",6379);
        System.out.println(jedis.ping());

        jedis.close();

    }
}
