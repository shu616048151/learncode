package com.shu.spring.timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author shuxibing
 * @date 2019/10/24 17:30
 * @uint d9lab
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TimerApp {

    public static ConcurrentHashMap<String, ScheduledFuture> map = new ConcurrentHashMap<String, ScheduledFuture>();


    public static void main(String[] args){
        SpringApplication.run(TimerApp.class,args);
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(5);
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }


}
