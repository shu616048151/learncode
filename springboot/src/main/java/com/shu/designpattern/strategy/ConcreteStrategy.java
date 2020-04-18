package com.shu.designpattern.strategy;

/**
 * @author shuxibing
 * @date 2019/8/6 11:09
 * @uint d9lab
 * @Description:
 */
public class ConcreteStrategy implements Strategy{
    @Override
    public String doSomething() {
        return "ConcreteStrategy do something";
    }
}
