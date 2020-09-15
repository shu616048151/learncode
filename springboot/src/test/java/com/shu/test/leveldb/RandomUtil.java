package com.shu.test.leveldb;

import java.util.Random;

/**
 * @Author shuxibing
 * @Date 2020/5/3 14:42
 * @Uint d9lab_2019
 * @Description:
 */
public class RandomUtil {
    public final static  String key="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String randomStr(int len){
        int keylen=key.length();
        StringBuilder stringBuilder=new StringBuilder();
        Random random=new Random();
        for (int i=0;i<len;i++){
            stringBuilder.append(key.charAt(random.nextInt(keylen)));
        }
        return stringBuilder.toString();
    }
}
