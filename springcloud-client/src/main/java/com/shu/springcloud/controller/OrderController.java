package com.shu.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shuxibing
 * @Date 2020/4/19 9:36
 * @Uint d9lab_2019
 * @Description:
 */
@RestController
public class OrderController {

    @RequestMapping("getOrder")
    public String getOrder(){
        return "this is order";
    }

    @RequestMapping("getOrder1")
    public String getOrder1(String name){
        return "hello:"+name+" this is order";
    }
}
