package com.shu.designpattern.strategy;

/**
 * @author shuxibing
 * @date 2019/8/6 11:10
 * @uint d9lab
 * @Description:
 */
public class TestStrategy implements Strategy {
    @Override
    public String doSomething() {
        return "TestStrategy do something";
    }
}
