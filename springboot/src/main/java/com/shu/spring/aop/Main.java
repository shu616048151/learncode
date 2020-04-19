package com.shu.spring.aop;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
    public static void main(String[] args){
        FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
        String[] aliases = applicationContext.getAliases("student");
        for (String alias : aliases) {
            System.out.println(alias);
        }
        Person student = (Person) applicationContext.getBean("student");
        student.say();

    }
}
