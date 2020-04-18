package com.shu.designpattern.proxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args){
        XiaoMing xiaoMing=new XiaoMing();
        MyJdkProxyInvocationHandler myJdkProxyInvocationHandler=new MyJdkProxyInvocationHandler(xiaoMing);
        House o = (House)Proxy.newProxyInstance(xiaoMing.getClass().getClassLoader(), xiaoMing.getClass().getInterfaces(), myJdkProxyInvocationHandler);
        o.buyHouse();


//        Enhancer enhancer=new Enhancer();
//        enhancer.setSuperclass(XiaoMing.class);
//        enhancer.setCallback(new CglibProxy());
//        House o = (House)enhancer.create();
//        o.buyHouse();
    }
}
