package com.springboot.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * @author shuxibing
 * @date 2020/2/11 11:41
 * @uint d9lab
 * @Description:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SpringmvcApp {
    public static void main(String[] args){
        SpringApplication.run(SpringmvcApp.class,args);
    }
}
