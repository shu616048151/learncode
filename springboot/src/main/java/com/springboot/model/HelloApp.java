package com.springboot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
    public static void main(String[] args) throws IOException {
//        Properties properties=new Properties();
//        InputStream inputStream=HelloApp.class.getClassLoader().getResourceAsStream("application-hello.properties");
//        properties.load(inputStream);
//        SpringApplication springApplication=new SpringApplication(HelloApp.class);
//        springApplication.setDefaultProperties(properties);
//        springApplication.run(args);
        SpringApplication.run(HelloApp.class,args);
    }

    @Autowired
    private HelloProperty helloProperty;
    @RequestMapping("/getProperty")
    public HelloProperty getProperty(){
        return helloProperty;
    }

    @RequestMapping("/shu")
    public String shu(){
        return "shu";
    }


}
