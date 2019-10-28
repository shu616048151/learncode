package com.shu.redis.miaosha.controller;

import com.shu.redis.antibrush.aspect.RequestLimit;
import com.shu.redis.miaosha.util.ActivityDynamicTask;
import com.shu.redis.miaosha.util.MyJedisPool;
import com.shu.redis.miaosha.util.PessLockThread;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shuxibing
 * @date 2019/10/26 20:42
 * @uint d9lab
 * @Description:
 */
@RestController
public class RedisController {

    private final static Logger logger = Logger.getLogger(RedisController.class);

   final String key="wecash";
    @Resource
    private ActivityDynamicTask activityDynamicTask;


    @RequestMapping("/init")
    public void init(){
        initNum(key, 500);
        activityDynamicTask.startCron();

    }

    @RequestMapping("/start")
    public String start(int people){
        long startTime=System.currentTimeMillis();
        //初始化秒杀的数量
        //当前的秒杀人数,线程池的效率高一些
        long endTime=startManyThread(key, people);
        System.out.println("总共执行时间："+(endTime-startTime)+"ms");
        return "启动成功";
    }

    @RequestMapping("/startTask")
    public void startTask(){
        activityDynamicTask.startCron();
    }



    @RequestLimit(maxCount = 10,expireTime = 10)
    @RequestMapping(value = "/trade/activity/startSecondKill")
    public String secondSkill(HttpServletRequest request) throws Exception {
       return "成功";
    }



    /**
     *
     * @param key 秒杀的商品
     * @param num 秒杀的数量
     */
    public static void initNum(String key,int num){
        Jedis jedis= MyJedisPool.getJedis();
        jedis.set(key, String.valueOf(num));
        Set<String> keys = jedis.keys("pess*");
        for (String key1:keys){
            jedis.del(key1);
        }
        jedis.close();
    }



    /**
     * 使用线程池的方式
     * @param key 秒杀的商品
     * @param n 秒杀的人数
     *
     */
    public static long startManyThread(String key,int n) {
        ExecutorService executor = Executors.newFixedThreadPool(30);
        if (n>0) {
            for (int i = 0; i < n; i++) {
                String id= UUID.randomUUID().toString().replace("-", "");
                //乐观锁方式
//				executor.execute(new OptiLockThread((i+1)+"用户ID:"+id, key));
                //悲观锁方式
                executor.execute(new PessLockThread((i+1)+"用户ID:"+id, key));
            }
        }
        //当前线程池等待完成
        executor.shutdown();
        //确认线程是否完全结束
        while(true){
            if (executor.isTerminated()) {
                System.out.println("线程池的线程全部结束");
                break;
            }
            try {
                //当前主线程休眠，等待一段时间在检测
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return System.currentTimeMillis();
    }



}
