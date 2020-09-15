package com.shu.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shu.dubbo.service.UserService;
import edu.whut.pocket.dubbo.service.IDubboUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shuxibing
 * @Date 2020/9/14 20:45
 * @Uint d9lab_2019
 * @Description:
 */

@RestController
public class DubboController {

//    @Reference
//    UserService userService;
//
//    @GetMapping("/sayHello")
//    public String sayHello(String name){
//        return userService.say(name);
//    }

    @Reference
    IDubboUserService dubboUserService;

        @GetMapping("/sayHello")
    public String sayHello(Integer id){
        return dubboUserService.getUserNameByUserId(id);
    }



}
