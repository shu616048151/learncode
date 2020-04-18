package com.shu.base.annotation;

/**
 * @author shuxibing
 * @date 2019/5/17 0017 16:25
 * @uint d9lab
 * @Description:
 */
@EntityClass("user")
public class User {
    @EntityField("id")
    private  String id;
    @EntityField("name")
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
