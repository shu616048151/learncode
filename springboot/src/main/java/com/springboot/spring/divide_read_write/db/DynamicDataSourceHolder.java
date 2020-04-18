package com.springboot.spring.divide_read_write.db;

/**
 * @author shuxibing
 * @date 2019/8/10 11:46
 * @uint d9lab
 * @Description:
 */
public class DynamicDataSourceHolder {
    //存放当前线程的一些信息
    public static final ThreadLocal<String> holder=new ThreadLocal<>();

    public static void setSalve(){
        holder.set("slave");
    }

    public static void setMaster(){
        holder.remove();
    }

    public static String getDataSource(){
        return holder.get();
    }

}
