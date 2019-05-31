package com.shu.base.exception;

/**
 * @Author shuxibing
 * @Date 2019/5/28 19:44
 * @Uint D9lab
 * @Description:
 */
public class CustomException extends Exception {
    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }
}
