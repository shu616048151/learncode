package test;

import com.shu.redis.miaosha.util.MyJedisPool;
import javafx.scene.layout.BorderImage;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.UUID;

/**
 * @author shuxibing
 * @date 2019/10/26 23:28
 * @uint d9lab
 * @Description:
 */
public class RedisTest {
    public static void main(String[] args){
        Jedis jedis = MyJedisPool.getJedis();
//        Set<String> keys = jedis.keys("secondKill*");
//        for (String key:keys){
//            jedis.del(key);
//        }
        Set<String> keys1 = jedis.keys("second*");
        for (String s : keys1) {
            jedis.del(s);
        }
        jedis.close();
        System.out.println("删除完成");

    }

    @Test
    public void test(){
        Jedis jedis = MyJedisPool.getJedis();
        jedis.set("name","张三");
        jedis.close();
    }

    @Test
    public void test2(){
        System.out.println(UUID.randomUUID().toString());
    }
}
