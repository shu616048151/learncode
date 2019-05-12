package com.shu.genericity;

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
    public static <T> T test(T t){
        return t;
    }
}
