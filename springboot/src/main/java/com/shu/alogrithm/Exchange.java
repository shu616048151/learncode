package com.shu.alogrithm;

import java.lang.reflect.Array;

/**
 * @Author shuxibing
 * @Date 2020/9/7 10:59
 * @Uint d9lab_2019
 * @Description:  动态规划找零钱问题
 */
public class Exchange {

    public static void main(String[] args){
        int[] coin={1,2};
        System.out.println(  coinChange(coin,3));
    }


    /**
     * 假设有 1 元，3 元，5 元的硬币若干（无限），现在需要凑出 11 元，问如何组合才能使硬币的数量最少？
     * 零钱数量无限 类似于完全背包问题
     * @param coin
     */
    public static int coinChange(int[] coin,int amount){
        int[] dp=new int[amount+1];
        for (int i=0; i< coin.length;i++){
            for (int j=0;j<=amount;j++){
                //类似于完全背包问题，一个硬币i可以使用多次
                for (int k=0;k*coin[i]<=j;k++) {
                    dp[j] = Math.min(dp[j], dp[j - k * coin[i]] + k);
                }
            }
        }
        return dp[amount] == 0 ? -1: dp[amount];
    }
}
