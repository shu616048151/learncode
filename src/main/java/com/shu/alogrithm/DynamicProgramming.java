package com.shu.alogrithm;

/**
 * @author shuxibing
 * @date 2019/12/14 9:55
 * @uint d9lab
 * @Description: 动态规划相关问题
 */
public class DynamicProgramming {
    public static void main(String[] args){
        int[] arr={1,2,-1,-3,5,6,-2};
        int length = LongestSubString(arr);
        System.out.println(length);
    }
    /**
     * 【题目】：
     *
     * 给定数组arr，返回arr的最长递增子序列。
     *
     * 【例子】：
     * arr=[10,22,9,33,21,50,41,60,80],返回的最长递增子序列为[10,22,33,41,60,80]长度为6。
     *
     * @param arr
     * @return
     */
    public static int LongestSubString(int arr[]) {
        int len = 0;
        if (arr == null || arr.length == 0){
            return 0;
        }
        int dp[] = new int[arr.length];
        dp[0] = 1;
        //dp[i] 表示到i为止是最长递增子序列的长度
        for (int i = 1; i < arr.length; i++) {
            dp[i] = 1;
            //遍历从0到i之间的序列，计算最大序列
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    //求dp[i]时遍历，dp[0...i-1],找出arr[j]<arr[i]小且dp[j]是最大的
                    //dp[i]=dp[j]+1;
                    //dp[i]表示加入arr[i]的数的，单调序列长度，并不代表此时是最长的序列
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        //求出最长单调子序列
        for (int i = 0; i < arr.length; i++) {
            len = Math.max(dp[i], len);
        }
        return len;
    }
}
