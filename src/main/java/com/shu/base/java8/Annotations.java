package com.shu.base.java8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shuxibing
 * @date 2019/6/29 16:35
 * @uint d9lab
 * @Description:
 */
public class Annotations {

    //注解
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE_PARAMETER,ElementType.TYPE_USE})
    public  @interface  NonEmpty{

    }

    public static class Holder< @NonEmpty T > extends @NonEmpty Object {
        public void method() throws @NonEmpty Exception {
        }
    }

}
