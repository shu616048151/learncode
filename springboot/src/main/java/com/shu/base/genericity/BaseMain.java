package com.shu.base.genericity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseMain {
    private Map<String,Object> map=new ConcurrentHashMap<>();
    public static void main(String[] args){
        Base<String> base=new Base<String>("zhangsan");
        String t = base.getT();
        System.out.println(t);

    }

    public <T> T getObject(Class<T> clazz)  {
        String name = clazz.getName();
        //首字母转为小写
        byte[] bytes = name.getBytes();
        bytes[0]+=32;
        name=String.valueOf(bytes);
        Object o = map.get(name);
        return (T)o;
    }
}
