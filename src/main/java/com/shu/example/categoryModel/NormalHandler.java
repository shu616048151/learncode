package com.shu.example.categoryModel;

import org.springframework.stereotype.Component;

/**
 * @author shuxibing
 * @date 2019/7/31 14:58
 * @uint d9lab
 * @Description:
 */
@Component
@HandlerType("1")
public class NormalHandler extends AbstractHandler {
    @Override
    public String handler(OrderDTO orderDTO) {
        return "处理普通订单";
    }
}
