package com.shu.designpattern.template;

/**
 * 模板设计模式，父类定义方法，子类实现方法。在new的时候，子类的方法会默认覆盖父类的方法
 */
/**
 *
 * @Author shuxibing
 * @Date 2020/4/26 14:29
 * @Uint d9lab
 * @Description:  方便提取公共部分，代码地区
 *
 */
public abstract class Factrory {
    public void say(){
    }
    //
    public abstract void show();

    public void execute(){
        //可以规定方法的执行顺序
        System.out.println("执行say方法");
        say();
        System.out.println("执行show方法");
        show();
    }
}
