package com.shu.designmodel.factory;

public class Main {
    public static void main(String[] args)  {
//        Car baoma = CarFactory.createBaoma();
////        System.out.println(baoma.getName());
////        Car auDi = CarFactory.createAuDi();
////        System.out.println(auDi.getName());
        Car car = CarFactory.createCar(Baoma.class);
        System.out.println(car.getName());
    }
}
