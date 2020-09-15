package com.shu.base.java8;

import java.util.Optional;

/**
 * @Author shuxibing
 * @Date 2020/5/4 20:59
 * @Uint d9lab_2019
 * @Description:
 */
public class OptionTest {
    public static void main(String[] args){
        Optional<Integer> optionalInteger=Optional.ofNullable(null);
        optionalInteger.orElse(222);
        System.out.println(optionalInteger.isPresent());
        System.out.println(optionalInteger.get());
    }
}
