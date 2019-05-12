package com.shu.designpattern.templatepattern;

/**
 * 模板设计模式，父类定义方法，子类实现方法。在new的时候，子类的方法会默认覆盖父类的方法
 */
public abstract class Factrory {
    public void say(){
        show();
    }
    //
    public abstract void show();
}
