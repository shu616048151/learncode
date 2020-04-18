package com.springboot.designpattern.singleton;

/**
 * 懒汉方式
 */
public class lanhan {
    private static lanhan lanhan;
    private lanhan(){}

    public synchronized static lanhan getSingleTon(){
        if (lanhan==null){
            lanhan=new lanhan();
        }
        return lanhan;
    }
}
