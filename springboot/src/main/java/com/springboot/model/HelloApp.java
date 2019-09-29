package com.springboot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuxibing
 * @date 2019/9/29 21:37
 * @uint d9lab
 * @Description:
 */
@SpringBootApplication
@PropertySource(value = "classpath:hello.properties")
@RestController
public class HelloApp {
    public static void main(String[] args){
        SpringApplication.run(HelloApp.class,args);
    }

    @Autowired
    private HelloProperty helloProperty;
    @RequestMapping("/getProperty")
    public HelloProperty getProperty(){
        return helloProperty;
    }


}
