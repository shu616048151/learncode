package com.shu.spring.timing;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shuxibing
 * @date 2019/8/9 10:40
 * @uint d9lab
 * @Description:
 */
@Component
public class CTask implements Task {
    @Scheduled(cron="0/20 * *  * * ? ")
    @Override
    public void task() throws InterruptedException {
        Thread.sleep(5000);
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(dateFormat.format(new Date())+"********C任务开始执行");

    }
}
