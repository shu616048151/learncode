package com.shu.designpattern.adapter;

/**
 * @author shuxibing
 * @date 2019/8/9 20:46
 * @uint d9lab
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        //使用适配器进行适配实现
        Duck duck = new TurkeyAdapter(turkey);
        duck.quack();
    }
}
