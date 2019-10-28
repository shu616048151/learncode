package com.shu.redis.miaosha.exception;

/**
 * @author shuxibing
 * @date 2019/10/28 16:16
 * @uint d9lab
 * @Description:
 */
public class RequestLimitException extends RuntimeException {
    public RequestLimitException(String message) {
        super(message);
    }
}
