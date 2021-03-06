package com.shu.redis.antibrush.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shuxibing
 * @date 2019/10/27 16:47
 * @uint d9lab
 * @Description:  接口防刷，对接口请求的次数做一定的限制
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLimit {
    //窗口区间的最大访问量
   int maxCount() default 5;
   //窗口单位
   int second() default 1;
   //关闭到小黑屋的时间
   long expireTime() default 5*60;

   //提示信息
   String message() default "短时间内访问次数超出限制";
}
