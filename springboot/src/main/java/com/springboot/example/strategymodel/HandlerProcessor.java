package com.springboot.example.strategymodel;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shuxibing
 * @date 2019/7/31 15:03
 * @uint d9lab
 * @Description: 该类主要指将扫描的信息注入applicationContext容器中
 */
@Component
public class HandlerProcessor implements BeanFactoryPostProcessor {

    /**
     * 扫描hanglerMap注解兵注入容器中
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String,Class> handlerMap=new HashMap<>(3);
        ClassScaner.scan(Const.HANDLER_PACAGER,HandlerType.class).forEach(clazz->{
            String type = clazz.getAnnotation(HandlerType.class).value();
            handlerMap.put(type,clazz);
        });
        HandlerContext context=new HandlerContext(handlerMap);
        beanFactory.registerSingleton(HandlerContext.class.getName(),context);
    }
}
