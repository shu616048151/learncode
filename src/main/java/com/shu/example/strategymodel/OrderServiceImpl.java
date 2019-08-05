package com.shu.example.strategymodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shuxibing
 * @date 2019/7/31 14:38
 * @uint d9lab
 * @Description:
 *实现的主体思路:
 * 第一步：通过扫描注入的方式注入到applicationContext的容器中
 * 第二步：从容器中获取信息，然后根据业务封装成接口
 * 第三步：调用接口的方式
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private HandlerContext handlerContext;
    @Override
    public String handle(OrderDTO orderDTO) {
        AbstractHandler handler = handlerContext.getInstance(orderDTO.getType());
        return handler.handler(orderDTO);
    }

    /**
     * 此方式为传统的方式，非常代码的维护和代码重构
     * @param orderDTO
     * @return
     */
    public String ordinaryHandle(OrderDTO orderDTO){
        String type = orderDTO.getType();
        if ("1".equals(type)){
            return "处理普通的类型";
        }if ("2".equals(type)){
            return "处理团购的类型";
        }if ("2".equals(type)){
            return "处理促销的类型";
        }
        return "错误处理";
    }
}
