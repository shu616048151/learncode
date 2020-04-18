package com.shu.spring.timer.controller;

import com.shu.spring.timer.TimerApp;
import com.shu.spring.timer.service.DynamicTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledFuture;

/**
 * @author shuxibing
 * @date 2019/10/25 17:10
 * @uint d9lab
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    private DynamicTask task;
    @RequestMapping("/start")
    public void test() throws Exception {
        // 开启定时任务，对象注解Scope是多利的。
        task.startCron("activity");

    }
    @RequestMapping("/stop")
    public void stop() throws Exception {
        // 提前测试用来测试线程1进行对比是否关闭。
        ScheduledFuture scheduledFuture = TimerApp.map.get("activity");
        if (scheduledFuture!=null){
            TimerApp.map.remove("activity");
            System.out.println("开始结束线程");
            scheduledFuture.cancel(true);
            // 查看任务是否在正常执行之前结束,正常true
            boolean cancelled = scheduledFuture.isCancelled();
            while (!cancelled) {
                scheduledFuture.cancel(true);
                cancelled = scheduledFuture.isCancelled();
            }
        }
    }
}
