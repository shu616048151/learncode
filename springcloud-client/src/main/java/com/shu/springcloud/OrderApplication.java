package com.shu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author shuxibing
 * @Date 2020/4/19 9:33
 * @Uint d9lab_2019
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderApplication {
    public static void main(String[] args){
        SpringApplication.run(OrderApplication.class,args);
    }
}
