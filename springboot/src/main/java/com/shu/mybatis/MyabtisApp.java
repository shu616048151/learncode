package com.shu.mybatis;

import com.shu.mybatis.mapper.UserMapper;
import com.shu.mybatis.model.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuxibing
 * @date 2019/10/5 17:18
 * @uint d9lab
 * @Description: springboot整合mybatis
 */
@SpringBootApplication
@MapperScan(value = "com.springboot.mybatis.mapper")
@RestController
public class MyabtisApp {
    public static void main(String[] args){
        SpringApplication.run(MyabtisApp.class,args);
    }

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getUser")
    public User getUser(Integer id){
        return userMapper.getUserById(id);
    }
}
