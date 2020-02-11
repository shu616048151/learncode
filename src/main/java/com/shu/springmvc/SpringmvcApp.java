package com.shu.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author shuxibing
 * @date 2020/2/11 11:41
 * @uint d9lab
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringmvcApp {
    public static void main(String[] args){
        SpringApplication.run(SpringmvcApp.class,args);
    }
}
