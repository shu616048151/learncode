package com.shu.test.leveldb;


import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author shuxibing
 * @Date 2020/5/30 9:01
 * @Uint d9lab_2019
 * @Description:
 */
public  class Test {
    private static Map<String,Object> map=new ConcurrentHashMap<>(10);

    static {
        map.put("student",new Student());
        map.put("teacher",new Teacher());
    }
    public static class Student{
       public static void say(){
           System.out.println("学生");
       }

    }
    public static class Teacher{}

    public <T> T getObject(Class<T> clazz)  {
        String name = clazz.getName();
        //首字母转为小写
        byte[] bytes = name.getBytes();
        bytes[0]+=32;
        name=String.valueOf(bytes);
        Object o = map.get(name);
        return (T)o;
    }
    public static void main(String[] args) {
        Test test=new Test();
        Student object = test.getObject( Student.class);
        object.say();
    }
}
