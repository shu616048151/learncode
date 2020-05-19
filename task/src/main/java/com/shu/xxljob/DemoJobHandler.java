package com.shu.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author shuxibing
 * @Date 2020/5/9 12:38
 * @Uint d9lab_2019
 * @Description:
 */
@Component
public class DemoJobHandler  {
    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        Date date=new Date();
        System.out.println("执行业务111："+date);
        return ReturnT.SUCCESS;
    }

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler11")
    public ReturnT<String> demoJobHandler11(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        Date date=new Date();
        System.out.println("demoJobHandler11："+date);
        return ReturnT.SUCCESS;
    }

}
