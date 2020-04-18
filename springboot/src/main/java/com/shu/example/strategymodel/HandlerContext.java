package com.shu.example.strategymodel;

import java.util.Map;

/**
 * @author shuxibing
 * @date 2019/7/31 14:39
 * @uint d9lab
 * @Description:
 */
public class HandlerContext {
    private Map<String,Class> handlerMap;

    public HandlerContext(Map<String, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public AbstractHandler getInstance(String type){
        Class clazz=handlerMap.get(type);
        if (clazz==null){
            throw new IllegalArgumentException("输入的参数类型有问题："+type);
        }
        return (AbstractHandler) BeanTool.getBean(clazz);
    }
}
