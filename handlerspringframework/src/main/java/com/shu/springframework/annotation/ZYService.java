package com.shu.springframework.annotation;

import java.lang.annotation.*;


/**
 * @Author shuxibing
 * @Date 2020/3/10 21:50
 * @Uint d9lab-2019
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZYService {
    String value() default "";
}
