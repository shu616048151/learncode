package com.springboot.spring.divide_read_write.db;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author shuxibing
 * @date 2019/8/10 11:44
 * @uint d9lab
 * @Description:
 */
public class DataSourceAspect implements MethodBeforeAdvice, AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {

    }
    //设置事务环绕处理
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        if (method.getName().startsWith("get")){
            //从从数据库中读取数据
            DynamicDataSourceHolder.setSalve();
        }else{
            DynamicDataSourceHolder.setMaster();

        }


    }
}
