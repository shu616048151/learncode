package com.shu.spring.timing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author shuxibing
 * @date 2019/8/9 10:31
 * @uint d9lab
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAsync
@EnableScheduling
public class TimingMain {
    public static void main(String[] args){
        SpringApplication.run(TimingMain.class,args);
    }
}
