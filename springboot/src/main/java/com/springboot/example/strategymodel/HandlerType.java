package com.springboot.example.strategymodel;

import java.lang.annotation.*;

/**
 * @author shuxibing
 * @date 2019/7/31 14:55
 * @uint d9lab
 * @Description:
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface HandlerType {
    String value();
}
