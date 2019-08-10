package com.shu.designpattern.adapter;

/**
 * @author shuxibing
 * @date 2019/8/9 20:45
 * @uint d9lab
 * @Description:
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("gobble!");
    }
}