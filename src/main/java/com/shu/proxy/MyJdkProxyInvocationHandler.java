package com.shu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyJdkProxyInvocationHandler implements InvocationHandler{
    private Object tarjet;

    public MyJdkProxyInvocationHandler(Object tarjet) {
        this.tarjet = tarjet;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始买房");
        //JAVA反射的代理,生成代理对象。
        Object object = method.invoke(tarjet, args);
        System.out.println("结束买房");
       // System.out.println(proxy.equals(object));
        return object;
    }
}
