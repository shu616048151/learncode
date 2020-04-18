package com.springboot.base.genericity;

public class BaseMain {
    public static void main(String[] args){
        Base<String> base=new Base<String>("zhangsan");
        String t = base.getT();
        System.out.println(t);

    }
}
