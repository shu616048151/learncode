package com.shu.alogrithm;

import java.util.*;

/**
 * @Author shuxibing
 * @Date 2020/4/25 14:42
 * @Uint d9lab_2019
 * @Description:
 */
public class Zhaoyin2 {
    public static void main(String[] args) {
        //1.读取数据
        Scanner scan = new Scanner(System.in);
        String strs [] = scan.nextLine().split(" ");
        //2.将数据存储在list中(保证不存在重复的数据)
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            if (!list.contains(Integer.parseInt(strs[i]))) {
                list.add(Integer.parseInt(strs[i]));
            }
        }
        // 3.将数据进行排序
        Collections.sort(list);
        int count = 0;
        // 4.对数据进行判断
        for (int i = 0; i < list.size()-1; i++) {
            for (int j = list.size()-1; j >=i; j--) {
                if (list.get(i)+list.get(j)<24) {
                    break;
                }
                if (list.get(i)+list.get(j)==24) {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
        scan.close();
    }

}
