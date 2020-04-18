package com.springboot.utils;


import cn.hutool.bloomfilter.BitSetBloomFilter;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.util.UUID;

/**
 * @Author shuxibing
 * @Date 2020/4/17 9:10
 * @Uint d9lab_2019
 * @Description: 高并发的布隆过滤器
 */
public class BloomFilterTest {
    private static int size = 1000000;//预计要插入多少数据

    private static double fpp = 0.01;//期望的误判率

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);
    public static void main(String[] args){
        //插入数据
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put(i);
        }
        int count = 0;
        for (int i = 1000000; i < 2000000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        System.out.println("总共的误判数:" + count+"误判率："+(count/1000000.0));
    }


    @Test
    public void test(){
        BitSetBloomFilter bitSetBloomFilter = new BitSetBloomFilter(90000, 90000, 10);
        for (int i=0;i<100000;i++){
            bitSetBloomFilter.add(UUID.randomUUID().toString());
        }
        int count=0;
        for (int i=0;i<100000;i++){
            String str = UUID.randomUUID().toString();
            if (bitSetBloomFilter.contains(str)){
                count++;
                System.out.println(str);
            }
        }
            System.out.println("误判数量："+count);

    }
}
