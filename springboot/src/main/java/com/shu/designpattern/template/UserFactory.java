package com.shu.designpattern.template;

public class UserFactory extends Factrory {
    @Override
    public void show() {
        System.out.println("这是子类实现方法");
    }
    public static void main(String[] args){
        UserFactory userFactory=new UserFactory();
        userFactory.execute();
    }
}
