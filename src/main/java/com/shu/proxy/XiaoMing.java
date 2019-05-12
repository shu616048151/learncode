package com.shu.proxy;

import org.springframework.stereotype.Component;

public class XiaoMing implements House {
    public void buyHouse() {
        System.out.println("我是小明，我想买房");
    }
}
