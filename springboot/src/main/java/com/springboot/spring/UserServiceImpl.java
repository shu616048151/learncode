package com.springboot.spring;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{
    public void show() {
        System.out.println("this is show method");
    }
}
