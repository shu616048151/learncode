package com.shu.designpattern.singleton;

/**
 * 饿汉模式
 */
public class ehan {
    private static ehan ehan=new ehan();
    private ehan(){}
    public synchronized static ehan getSingleTon(){
        return ehan;
    }
}
