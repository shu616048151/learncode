package com.shu.example.categorymodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuxibing
 * @date 2019/8/4 11:14
 * @uint d9lab
 * @Description: 策略模式测试选项
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/getMessage")
    public String getMessage(String type){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setType(type);
        return orderService.handle(orderDTO);
    }
}
