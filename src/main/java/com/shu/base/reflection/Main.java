package com.shu.base.reflection;


import com.shu.domain.User;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> clazz= Class.forName("com.shu.domain.User");
        Object o1 = clazz.newInstance();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
      /*  Method setId = clazz.getMethod("setId",clazz.getField("id").getty);
        setId.invoke(o1,123);
        User o11 = (User) o1;
        System.out.println(o11.getId());*/

    }
    @Test
    public void test(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("id",123);
        map.put("name","李四");
        User o = (User)InsertArgurement.map2Bean("com.shu.domain.User", map);
        System.out.println(o.getId()+o.getName());

    }
    @Test
    public void test1() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> clazz= Class.forName("com.shu.domain.User");
        Object o1 = clazz.newInstance();
        Field field = clazz.getDeclaredField("id");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field1 : fields) {
            System.out.println(field1.getName());
        }
        if (!Modifier.isPublic(field.getModifiers())){
            field.setAccessible(true);
        }
        field.set(o1,123);
        System.out.println(((User)o1).getId());
    }
}
