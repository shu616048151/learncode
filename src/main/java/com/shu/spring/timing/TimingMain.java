package com.shu.spring.timing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shuxibing
 * @date 2019/8/9 10:31
 * @uint d9lab
 * @Description:
 */
public class TimingMain {
    public static void main(String[] args){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext-threadpool.xml");
    }
}
