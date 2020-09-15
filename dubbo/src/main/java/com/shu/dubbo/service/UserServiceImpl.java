package com.shu.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @Author shuxibing
 * @Date 2020/9/14 20:35
 * @Uint d9lab_2019
 * @Description:
 */
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Override
    public String say(String word) {
        System.out.println(word);
        return word;
    }
}
