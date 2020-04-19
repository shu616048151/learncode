package com.shu.springcloud.rpc.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author shuxibing
 * @Date 2020/4/19 9:50
 * @Uint d9lab_2019
 * @Description:
 */
@Service
@FeignClient(value = "order-server",fallback = OrderFeignError.class)
public interface OrderFeign {
    @RequestMapping("/getOrder")
    public String orderTest();

    @RequestMapping("/getOrder11")
    public String getOrder1(@RequestParam("name") String name);
}
