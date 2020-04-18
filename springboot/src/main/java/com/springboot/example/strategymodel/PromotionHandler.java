package com.springboot.example.strategymodel;

import org.springframework.stereotype.Component;

/**
 * @author shuxibing
 * @date 2019/7/31 15:01
 * @uint d9lab
 * @Description:
 */
@Component
@HandlerType("3")
public class PromotionHandler extends AbstractHandler {
    @Override
    public String handler(OrderDTO orderDTO) {
        return "处理团购订单";
    }
}
