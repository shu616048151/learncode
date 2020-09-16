package com.shu.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author shuxibing
 * @Date 2020/9/15 21:15
 * @Uint d9lab_2019
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DockerApplication {
    public static void main(String[] args){
        SpringApplication.run(DockerApplication.class,args);
    }
}
