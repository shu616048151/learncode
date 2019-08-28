package com.shu.base.enums;

/**
 * @author shuxibing
 * @date 2019/8/28 10:27
 * @uint d9lab
 * @Description:  枚举类型在实际项目中的应用方法，通常可以使用一些常数的类型。比如一些多个同类型的多个常数
 */
public enum SignType {
    /**
     * HMAC-SHA256 加密
     */
    HMACSHA256("HMAC-SHA256"),
    /**
     *  MD5 加密
     */
    MD5("MD5");

    SignType(String type) {
        this.type = type;
    }

    private final String type;

    public String getType() {
        return type;
    }
}