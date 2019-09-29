package com.springboot.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shuxibing
 * @date 2019/9/29 21:33
 * @uint d9lab
 * @Description:  配置properties读取信息封装成一个类
 */
@Component
@ConfigurationProperties(prefix = "hello")
public class HelloProperty {
    private Integer id;
    private String name;
    private String age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
