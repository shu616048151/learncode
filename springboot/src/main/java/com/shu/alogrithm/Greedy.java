package com.shu.alogrithm;

/**
 * @Author shuxibing
 * @Date 2020/9/7 19:26
 * @Uint d9lab_2019
 * @Description:
 */
public class Greedy {

    /**
     * 贪心算法  股票最大利润
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int out=0;
        for (int i=1;i<prices.length;i++){
            out+=Math.max(0,prices[i]-prices[i-1]);
        }

        return 0;
    }
}
