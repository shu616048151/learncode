package com.shu.xxljob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author shuxibing
 * @Date 2020/5/9 12:16
 * @Uint d9lab_2019
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class XxlJobApplication {
    public static void main(String[] args){
        SpringApplication.run(XxlJobApplication.class,args);
    }
}
