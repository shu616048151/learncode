package com.springboot.base.java8;

/**
 * @author shuxibing
 * @date 2019/6/29 8:03
 * @uint d9lab
 * @Description:
 */
public interface InterfaceTest {
    //默认的方法形式
    default String notRequire(){
        return "没有指定的方法";
    }
}
