package com.springboot.spring.timing;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author shuxibing
 * @date 2019/8/9 10:29
 * @uint d9lab
 * @Description:
 */
@Component
public class BTask implements Task {
    private static final Logger logger= Logger.getLogger(BTask.class);
    @Scheduled(cron="0/5 * *  * * ? ")
    @Override
    @Async
    public void task() {
        logger.info("log b的日志系统");
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(Thread.currentThread().getName()+dateFormat.format(new Date())+"*********B任务每5秒执行一次进入测试");
    }
}
