package com.springboot.utils.hutool;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.FIFOCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.core.date.format.FastDateFormat;
import org.junit.Test;

import java.util.Date;
import java.util.TimeZone;

/**
 * @author shuxibing
 * @date 2019/9/24 14:51
 * @uint d9lab
 * @Description:
 */
public class HutoolTest {
    public static void main(String[] args){

    }

    @Test
    public void date(){
        Date date=new Date();
        FastDateFormat instance = FastDateFormat.getInstance("yyyy-MM-dd", TimeZone.getDefault());
        System.out.println(instance.format(date));
    }

    /**
     * 使用concurrenthashmap作为底层的存储容器
     */
    @Test
    public void cache(){
        FIFOCache<Object, Object> cache = CacheUtil.newFIFOCache(10);
        cache.put("name","zhangsan");
        cache.put("name","lisi");
        cache.put("age",18);
        System.out.println(cache.get("age"));
    }



    @Test
    public void crypto(){
        CaptchaUtil.createCircleCaptcha(20, 20);
    }




}
