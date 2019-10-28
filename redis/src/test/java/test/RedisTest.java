package test;

import com.shu.redis.miaosha.util.MyJedisPool;
import redis.clients.jedis.Jedis;

/**
 * @author shuxibing
 * @date 2019/10/26 23:28
 * @uint d9lab
 * @Description:
 */
public class RedisTest {
    public static void main(String[] args){
        Jedis jedis = MyJedisPool.getJedis();
//        Set<String> keys = jedis.keys("tradeId*");
//        for (String key:keys){
//            jedis.del(key);
//        }
//
        String s = jedis.get("requestLimit:/trade/activity/startSecondKill-127.0.0.1");
        System.out.println(s);
        jedis.close();

    }
}
