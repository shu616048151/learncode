package com.shu.alogrithm;

import java.util.Arrays;

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
     */
    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
