package com.shu.quartz.springboot.config;

import com.shu.quartz.springboot.DateTimeJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * @Author shuxibing
 * @Date 2020/2/27 20:44
 * @Uint d9lab-2019
 * @Description:
 */
@Configuration
public class QuartzConfig {

    @Bean
    public Scheduler scheduler() throws Exception {
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        scheduler.start();
        return scheduler;
    }
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setSchedulerName("Cluster_Scheduler");
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        factory.setTaskExecutor(schedulerThreadPool());
        factory.setTriggers(trigger1().getObject());
        return factory;
    }

    @Bean
    public JobDetailFactoryBean job1() {
        JobDataMap jobDataMap=new JobDataMap();
        jobDataMap.put("msg","我是舒志阳");
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(DateTimeJob.class);
        jobDetailFactoryBean.setJobDataMap(jobDataMap);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }
    @Bean
    public CronTriggerFactoryBean trigger1() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(job1().getObject());
        cronTriggerFactoryBean.setCronExpression("0/3 * * * * ?");
        return cronTriggerFactoryBean;
    }
    @Bean
    public Executor schedulerThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(15);
        executor.setMaxPoolSize(25);
        executor.setQueueCapacity(100);
        return executor;
    }

}
