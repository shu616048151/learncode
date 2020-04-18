package com.shu.base.annotation;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shuxibing
 * @date 2019/5/17 0017 16:26
 * @uint d9lab
 * @Description: 自定义注解解析
 *  一般使用的方式可以使自定义注解+AOP或者自定义读取某个数据和数据配置等问题
 */

public class AnnotationTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String path="com.shu.base.com.shu.springframework.annotation.User";
        Class<?> clazz=Class.forName(path);
        Object o = clazz.newInstance();
        EntityClass declaredAnnotation = clazz.getDeclaredAnnotation(EntityClass.class);
        System.out.println(declaredAnnotation.value());
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
            System.out.println(declaredField.getType().getName());
            EntityField entityField = declaredField.getDeclaredAnnotation(EntityField.class);
            if (entityField!=null){
                if (!Modifier.isPublic(declaredField.getModifiers())){
                    //修改私有属性
                    declaredField.setAccessible(true);
                }
                declaredField.set(o,"123");
                System.out.println("注解里面的信息:"+entityField.value());
            }
            User user=(User)o;
            System.out.println(user.getId());
        }
    }
    @Test
    public void reflection(){
        Map<String ,Object> map=new HashMap<>();
        map.put("id","1123");
        map.put("name","zhangsan");
        map.put("age",23);
        User user = InsertArgurement.map2Bean(User.class, map);
        System.out.println(user);
    }
}
