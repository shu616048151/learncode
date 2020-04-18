package com.springboot.jwt;

import java.util.UUID;

/**
 * @author shuxibing
 * @date 2020/2/10 11:30
 * @uint d9lab
 * @Description:
 */
public class Constant {

    public static final String JWT_ID = UUID.randomUUID().toString();

    /**
     * 加密密文
     */
    public static final String JWT_SECRET = "woyebuzhidaoxiediansha";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
}
