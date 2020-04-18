package com.springboot.designpattern.strategy;

/**
 * @author shuxibing
 * @date 2019/8/6 11:12
 * @uint d9lab
 * @Description:
 */
public class Main {
    public static void main(String[] args){
        Strategy strategy=new ConcreteStrategy();
        StrategyContext context=new StrategyContext(strategy);
        String doAnything = context.doAnything();
        System.out.println(doAnything);
    }
}
