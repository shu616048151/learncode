package com.springboot.base.enums;

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
        SignType md5 = SignType.MD5;
        System.out.println(md5);
        System.out.println(md5.getType());
        String type = SignType.HMACSHA256.getType();
        System.out.println(type);
    }
}
