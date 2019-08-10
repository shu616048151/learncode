package com.shu.spring.timing;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author shuxibing
 * @date 2019/8/9 10:27
 * @uint d9lab
 * @Description:
 */
@Component
public class ATask implements Task {
    @Scheduled(cron="0/10 * *  * * ? ")
    @Override
    public void task() {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
            }
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(sdf.format(new Date())+"*********A任务每10秒执行一次进入测试");

    }
}
