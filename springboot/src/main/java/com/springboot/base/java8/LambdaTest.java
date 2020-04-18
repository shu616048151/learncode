package com.springboot.base.java8;

import java.util.Arrays;

/**
 * @author shuxibing
 * @date 2019/6/29 8:00
 * @uint d9lab
 * @Description:
 */
public class LambdaTest {
    public static void main(String[] args){
        Arrays.asList( "a", "b", "d" ).forEach(e -> System.out.println( e ) );
    }
}
