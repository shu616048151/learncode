package com.shu.example.strategymodel;

import org.springframework.stereotype.Component;

/**
 * @author shuxibing
 * @date 2019/7/31 14:59
 * @uint d9lab
 * @Description:
 */
@Component
@HandlerType("2")
public class GroupHandler extends AbstractHandler {
    @Override
    public String handler(OrderDTO orderDTO) {
        return "处理团购的类型";
    }
}
