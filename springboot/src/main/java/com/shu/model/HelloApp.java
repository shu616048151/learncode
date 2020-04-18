package com.shu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author shuxibing
 * @date 2019/9/29 21:37
 * @uint d9lab
 * @Description:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@PropertySource(value = "classpath:hello.properties")
@RestController
public class HelloApp {
    public static void main(String[] args) throws IOException {

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
