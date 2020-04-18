package com.springboot.base.exception;

/**
 * @Author shuxibing
 * @Date 2019/5/28 19:46
 * @Uint D9lab
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws CustomException {
        throw new CustomException("自定义异常");
    }
}
