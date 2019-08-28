package com.shu.base.enums;

/**
 * @author shuxibing
 * @date 2019/8/28 10:27
 * @uint d9lab
 * @Description:
 */
public class Main {
    public static void main(String[] args){
        for (SignType signType:SignType.values()){
            System.out.println(signType.getType());
        }
    }
}
