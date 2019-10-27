package com.shu.redis.miaosha.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author shuxibing
 * @date 2019/10/25 17:29
 * @uint d9lab
 * @Description: 通过定时任务调度处理超时未付款订单
 */
@Component
public class ActivityDynamicTask {

    private final static Logger logger = Logger.getLogger(ActivityDynamicTask.class);

    public static ConcurrentHashMap<String, ScheduledFuture> taskMap = new ConcurrentHashMap<String, ScheduledFuture>();

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;


    Jedis jedis = MyJedisPool.getJedis();

    final String key="lock";
    final long timeout=1000;


    private String cron;

    private ScheduledFuture future;


    public void startCron() {
        cron = "0/1 * * * * ?";
        System.out.println(Thread.currentThread().getName());
        String name = Thread.currentThread().getName();
        if (taskMap.get("activity") == null) {
            future = threadPoolTaskScheduler.schedule(new myTask(name), new CronTrigger(cron));
            taskMap.put("activity", future);
        }
    }

    public void stop() {
        if (future != null) {
            future.cancel(true);
        }
    }

    private class myTask implements Runnable {
        private String name;

        myTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
          logger.info("开始处理未支付订单");
          try {
              Set<String> keys = jedis.keys("pess:*");
              for (String key:keys){
                  Long  oldTime = Long.valueOf(jedis.get(key));
                  long newTime = System.currentTimeMillis();
                  if (newTime-oldTime>3*1000){
                      long end=System.currentTimeMillis()+timeout;
                      boolean lock=lock(key, String.valueOf(end));
                      if (lock){
                          String oldlNum= jedis.get("wecash");
                          logger.info("更新前商品余数:"+oldlNum);
                          jedis.incr("wecash");
                          String newNum = jedis.get("wecash");
                          System.out.println("更新后商品余数:"+newNum);
                          unlock(key, String.valueOf(end));
                          jedis.del(key);
                          jedis.close();
                      }
                  }
              }
          }catch (Exception e){
              jedis.close();
              //重新连接
              jedis=MyJedisPool.getJedis();

          }



        }

    }


    public boolean lock(String key, String value){
        //jedis.setNx()如果原来有锁，就会上锁不成功，没有锁就直接setkey
        if(jedis.setnx(key, value)==1){//setNX 返回boolean
            return true;
        }
        //如果锁超时,重新设置新的锁
        String currentValue = jedis.get(key);
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue)<System.currentTimeMillis()){
            //获取上一个锁的时间，比较value有没有变化，查看是否上锁成功
            String oldvalue  = jedis.getSet(key,value);
            if(!StringUtils.isEmpty(oldvalue)&&oldvalue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }
    /***
     * 解锁
     * @param key
     * @param value
     * @return
     */
    public void unlock(String key,String value){
        try {
            String currentValue = jedis.get(key);
            //现在从redis中取的value值和传进来的value值一致，则解锁成功，否则说明之前的锁已经失效了。
            if(!StringUtils.isEmpty(currentValue)&&currentValue.equals(value)){
                jedis.del(key);
            }
        } catch (Exception e) {
            System.out.println("解锁异常");
        }
    }

}
