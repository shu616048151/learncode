package com.springboot.spring;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl2 implements  UserService{
    public void show() {
        System.out.println("this is UserServiceImpl2 show method");
    }
}
