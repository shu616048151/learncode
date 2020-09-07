package com.shu.alogrithm;

/**
 * @author shuxibing
 * @date 2019/12/14 9:55
 * @uint d9lab
 * @Description: 动态规划相关问题
 *
 * 动态规划的两种方法：①自顶向下的备忘录法 ②自底向上。
 * 1 状态转移方程
 * 2 边界条件（跳出循环递归的条件）
 *
 * 套路：
 * # 初始化 base case
 * dp[0][0][...] = base
 * # 进行状态转移
 * for 状态1 in 状态1的所有取值：
 *     for 状态2 in 状态2的所有取值：
 *         for ...
 *             dp[状态1][状态2][...] = 求最值(选择1，选择2...)
 *
 *
 */
public class DynamicProgramming {
    public static void main(String[] args){
        int[] arr={1,2,-1,-3,5,6,-2};
        int length = LongestSubString(arr);
        System.out.println(length);
    }


    /**
     * 自顶向下的备忘录法，从上向下一直触底
     * @param n
     * @return
     */
    public static int Fibonacci(int n)
    {
        if(n<=0)
            return n;
        int []Memo=new int[n+1];
        for(int i=0;i<=n;i++)
            Memo[i]=-1;
        return fib(n, Memo);
    }


    public static int fib(int n,int []Memo)
    {

        if(Memo[n]!=-1)
            return Memo[n];
        //如果已经求出了fib（n）的值直接返回，否则将求出的值保存在Memo备忘录中。
        if(n<=2)
            Memo[n]=1;

        else Memo[n]=fib( n-1,Memo)+fib(n-2,Memo);

        return Memo[n];
    }

    /**
     * 自底向上的动态规划，从底层向上堆叠
     * @param n
     * @return
     */
    public static int fib(int n)
    {
        if(n<=0){
            return n;
        }
        int []Memo=new int[n+1];
        Memo[0]=0;
        Memo[1]=1;
        for(int i=2;i<=n;i++)
        {
            Memo[i]=Memo[i-1]+Memo[i-2];
        }
        return Memo[n];
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
