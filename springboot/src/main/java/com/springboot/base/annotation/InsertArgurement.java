package com.springboot.base.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

public class InsertArgurement {

    /**
     * 根据 传入的map与类名，通过反射实例化对象
     * @param className  需要实例化的类名
     * @param map 包含属性的键值对 （String，Object）
     * @return 实例化成功的对象
     */
    public static Object map2Bean(String className,Map<String,Object> map){
        Object obj=null;
        try {
            //根据类名实例化对象
            obj=Class.forName(className).newInstance();

            for(String key :map.keySet()){
                if(key!=null&&!"".equals(key)){

                    //将属性首字符大写
                    Character c=key.charAt(0);
                    c=Character.toUpperCase(c);
                    String temp=String.valueOf(c);
                    if(key.length()>1)
                        temp+=key.substring(1);

                    Method methods[]=obj.getClass().getMethods();
                    for(int i=0;i<methods.length;++i){
                        //判断方法名是否匹配
                        if(methods[i].getName().contains("set"+temp))
                        {
                            //执行方法，传入值
                            methods[i].invoke(obj, map.get(key));
                            break;
                        }
                    }
                }
            }
//            Method method=obj.getClass().getMethod("toString");
//            method.invoke(obj, null);
        } catch (InstantiationException e) {

            e.printStackTrace();
        } catch (IllegalAccessException e) {

            e.printStackTrace();
        } catch (SecurityException e) {

            e.printStackTrace();
        } catch (IllegalArgumentException e) {

            e.printStackTrace();
        } catch (InvocationTargetException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        return obj;

    }
    public static <T> T map2Bean(Class<T> clazz,Map<String,Object> map){
        Object t=null;
        try {
            t= clazz.newInstance();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {

             if (map.containsKey(field.getName())){
                 Object value = map.get(field.getName());
                 if (value!=null){
                     if (!Modifier.isPublic(field.getModifiers())){
                         field.setAccessible(true);
                     }
                     field.set(t,value);
                 }
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (T) t;
    }

}
