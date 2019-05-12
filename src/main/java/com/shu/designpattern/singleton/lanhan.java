package com.shu.designpattern.singleton;

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
