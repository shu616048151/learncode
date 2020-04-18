package com.shu.base.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author shuxibing
 * @date 2019/5/17 0017 16:20
 * @uint d9lab
 * @Description:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EntityField {
    String value() default "";
}
