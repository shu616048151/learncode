package com.shu.springmvc.exception;

/**
 * @author shuxibing
 * @date 2019/10/29 14:43
 * @uint d9lab
 * @Description:
 */
public class RequestLimitException extends RuntimeException {
    public RequestLimitException(String message) {
        super(message);
    }
}
