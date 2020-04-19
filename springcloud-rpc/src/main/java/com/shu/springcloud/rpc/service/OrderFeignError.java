package com.shu.springcloud.rpc.service;

import org.springframework.stereotype.Component;

/**
 * @Author shuxibing
 * @Date 2020/4/19 10:08
 * @Uint d9lab_2019
 * @Description:
 */
@Component
public class OrderFeignError implements OrderFeign {
    @Override
    public String orderTest() {
        return "orderTest方法执行出错";
    }

    @Override
    public String getOrder1(String name) {
        return "getOrder1方法执行出错";
    }
}
