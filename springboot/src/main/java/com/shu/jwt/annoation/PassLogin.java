package com.shu.jwt.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author shuxibing
 * @Date 2020/9/8 14:56
 * @Uint d9lab_2019
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PassLogin {
    boolean require() default false;
}
