package com.shu.base.genericity;
/**
 *
 * @Author shuxibing
 * @Date 2020/5/30 10:06
 * @Uint d9lab
 * @Description:  java泛型
 *
 */
public class Base<T> {
    private Class<T> entityClass;
    private T t;

    public Base() {

    }

    public Base(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
