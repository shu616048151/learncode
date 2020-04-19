package com.shu.springcloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author shuxibing
 * @Date 2020/4/18 21:42
 * @Uint d9lab_2019
 * @Description:
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer01 {
    public static void main(String[] args){
        SpringApplication.run(EurekaServer01.class,args);
    }
}
