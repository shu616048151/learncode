package com.shu.fastdfs.timer.service;

import com.shu.fastdfs.timer.TimerApp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

/**
 * @author shuxibing
 * @date 2019/10/25 17:04
 * @uint d9lab
 * @Description:
 */
@Service
public class DynamicTask {
    private final static Logger logger= Logger.getLogger(DynamicTask.class);

    private String cron;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private ScheduledFuture future;

    public void startCron(String taskName) {
        cron = "0/1 * * * * ?";
        System.out.println(Thread.currentThread().getName());
        String name = Thread.currentThread().getName();
        if (TimerApp.map.get(taskName)==null){
            future = threadPoolTaskScheduler.schedule(new myTask(name), new CronTrigger(cron));
            TimerApp.map.put(taskName, future);
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
            System.out.println("test" + name);
        }
    }


}
