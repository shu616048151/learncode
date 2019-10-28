package com.shu.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author shuxibing
 * @date 2019/10/27 11:42
 * @uint d9lab
 * @Description:
 */
public class PrintWordsJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取数据
        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap().get("shujob"));
        System.out.println(jobExecutionContext.getTrigger().getJobDataMap().get("shutrigger"));

        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        System.out.println("PrintWordsJob start at:" + printTime + ", prints: Hello Job-" + new Random().nextInt(100));

    }
}
