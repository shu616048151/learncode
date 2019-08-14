package com.shu.designpattern.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("我是买房中介 ， 开始监听你买房了....");
        //代理类方法执行父类的方法
        Object invokeSuper = methodProxy.invokeSuper(o,args);
        System.out.println("我是买房中介 ， 开结束你买房了....");
        return invokeSuper;

    }
}
