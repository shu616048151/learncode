package com.shu.redis.miaosha.util;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author shuxibing
 * @date 2019/10/26 23:28
 * @uint d9lab
 * @Description:
 */
public class RedisTest {
    public static void main(String[] args){
        Jedis jedis = MyJedisPool.getJedis();
        Set<String> keys = jedis.keys("tradeId*");
        for (String key:keys){
            jedis.del(key);
        }
        System.out.println("删除完成");
        jedis.close();

    }
}
