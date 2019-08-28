package com.shu.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author shuxibing
 * @date 2019/8/14 10:17
 * @uint d9lab
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
