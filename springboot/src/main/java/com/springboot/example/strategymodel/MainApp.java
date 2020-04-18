package com.springboot.example.strategymodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author shuxibing
 * @date 2019/8/4 14:28
 * @uint d9lab
 * @Description:
 */
//去除某项配置
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MainApp {
    public static void main(String[] args){
        SpringApplication.run(MainApp.class,args);
    }
}
