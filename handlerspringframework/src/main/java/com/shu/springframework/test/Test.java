package com.shu.springframework.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.csource.common.NameValuePair;
/**
 * @Author shuxibing
 * @Date 2020/3/11 15:24
 * @Uint d9lab_2019
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws IOException {


    }

    @org.junit.Test
    public void  test() throws IOException {
        Properties properties=new Properties();
//        FileInputStream resourceAsStream=new FileInputStream("config.properties");
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        if (resourceAsStream!=null){
            properties.load(resourceAsStream);
            System.out.println(properties.getProperty("scanPackage"));
        }else {
            System.out.println("null");
        }
    }
}
