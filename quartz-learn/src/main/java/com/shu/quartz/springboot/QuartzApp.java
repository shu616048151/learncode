package com.shu.quartz.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author shuxibing
 * @Date 2020/2/27 20:38
 * @Uint d9lab-2019
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
public class QuartzApp {
    public static void main(String[] args){
        SpringApplication.run(QuartzApp.class,args);

    }
}
