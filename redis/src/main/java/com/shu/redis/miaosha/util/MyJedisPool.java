package com.shu.redis.miaosha.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis池
 */
public class MyJedisPool {
	private static JedisPool jedisPool=null;
	static{
			if(jedisPool == null) {
				JedisPoolConfig config = new JedisPoolConfig();
		        // 设置最大连接数
		        config.setMaxTotal(100);
		        // 设置最大空闲数
		        config.setMaxIdle(80);
		        // 设置最大等待时间
		        config.setMaxWaitMillis(3000);
		        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
		        config.setTestOnBorrow(true);

//		        config.setTestOnReturn(false);
//
//		        config.setBlockWhenExhausted(true);

		        //本机
//				jedisPool = new JedisPool(config,"127.0.0.1",6379,3000);
				jedisPool = new JedisPool(config,"kdgg.d9lab.net",6479,3000,"D9Lab171829");
				//阿里云
//				jedisPool = new JedisPool(config,"101.200.59.105",6379,3000,"shuxibing");

			}
		}
	public static Jedis getJedis(){
		//使用jedis的连接池，提高性能
		return jedisPool.getResource();
	}

}
