package com.shu.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author shuxibing
 * @Date 2020/9/14 20:33
 * @Uint d9lab_2019
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDubbo
@EnableDubboConfiguration
public class DubboConsumerApplication {
    public static void main(String[] args){
        SpringApplication.run(DubboConsumerApplication.class,args);
    }
}
