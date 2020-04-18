package com.springboot.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Student implements Person {
    public void say() {
        System.out.println("hello,world");
    }
}
