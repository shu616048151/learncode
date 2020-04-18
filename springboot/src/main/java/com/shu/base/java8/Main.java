package com.shu.base.java8;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author shuxibing
 * @date 2019/6/29 8:07
 * @uint d9lab
 * @Description:
 */
public class Main {
    @Test
    public void interfaceTest(){
        InterfaceImpl anInterface = new InterfaceImpl();
        System.out.println(anInterface.notRequire());
    }
    @Test
    public void LambdaTest(){
        Arrays.asList( "a", "b", "d" ).forEach(e -> System.out.println( e ) );
    }
    @Test
    public void methodTest(){
       
    }
}
