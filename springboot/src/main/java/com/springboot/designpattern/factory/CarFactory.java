package com.springboot.designpattern.factory;

/**
 * 工厂设计模式
 */
public class CarFactory {
    public static Car createBaoma(){
        return new Baoma();
    }
    public static Car createAuDi(){
        return new AuDi();
    }

    /**
     * 动态工厂的方式
     * @param clazz
     * @return
     */
    public static Car createCar(Class<? extends Car> clazz) {
        Car car=null;
        try {
            car=  clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return car;
    }

    public static <T> T getCar(Class<T> clazz){
        T t=null;
        try {
            t=clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
