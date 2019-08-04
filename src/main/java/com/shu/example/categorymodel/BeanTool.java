package com.shu.example.categorymodel;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author shuxibing
 * @date 2019/7/31 14:45
 * @uint d9lab
 * @Description:
 * 通过注入容器中获取applicationContext
 */
@Component
public class BeanTool implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    //自动初始化注入applicationContext
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (applicationContext==null){
            applicationContext=context;
        }
    }

    /**通过bean的缩写的方式获取类型
     *
     * @param name
     * @return
     */
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}
