package com.shu.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shuxibing
 * @date 2019/5/17 0017 16:24
 * @uint d9lab
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EntityClass {
    String value() default "";
}
