package com.shu.base.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shuxibing
 * @date 2019/9/30 10:59
 * @uint d9lab
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
public class ConcurrentApp {
    public static void main(String[] args){
        SpringApplication.run(ConcurrentApp.class,args);
    }
    @Bean(name = "concurrentHashMap")
    public Map<String,String> registerMap(){
        return new ConcurrentHashMap<>();
    }


    @Resource
    private Map concurrentHashMap;

    @RequestMapping("/addItem")
    public void addItem(String item){
        concurrentHashMap.put("item",item);
    }

    @RequestMapping("/getItem")
    public String getItem(String item){
        return (String) concurrentHashMap.get("item");
    }






}
