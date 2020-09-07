package com.shu.alogrithm.mianshi;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author shuxibing
 * @Date 2020/4/25 14:42
 * @Uint d9lab_2019
 * @Description:
 */
public class Zhaoyin1 {
    static Set<Set<Integer>> setSet;

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int n = scanner.nextInt();
        int[] num=new int[n];
        for(int i=0;i<n;i++){
            num[i]=scanner.nextInt();
        }
        dfs(0,0,num,new HashSet<>());
        System.out.println(setSet.size());
    }

    public static void dfs(int sum,int i,int[] arr, Set<Integer> set){
        if (i == arr.length ){
            return;
        }
        if (sum == 24) {
            setSet.add(set);
            return;
        }
        dfs(sum, i + 1,arr, set); // 不选择pos
        set.add(arr[i]); //选择pos
        dfs(sum + arr[i], i+1,arr, set);
    }

}
