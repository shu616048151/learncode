package com.springboot.designpattern.adapter;

/**
 * @author shuxibing
 * @date 2019/8/9 20:45
 * @uint d9lab
 * @Description: 适配器模式：就是把一个类接口转换成另一个用户需要的接口。
 */
public class TurkeyAdapter implements Duck {
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    //进行封装转换实现
    @Override
    public void quack() {
        turkey.gobble();
    }
}