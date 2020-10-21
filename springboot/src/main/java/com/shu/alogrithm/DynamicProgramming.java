package com.shu.alogrithm;

import org.hibernate.sql.OracleJoinFragment;

import java.util.ArrayList;
import java.util.List;

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
//       int[] w={3,5,2,6,4};
//       int[] v={4,4,3,5,3};
//       int[] num={1,1,1,1,1};
//        System.out.println(package01(w,v,w.length,4));
//        System.out.println(packageFull(w,v,w.length,4));
//        System.out.println(packageMany(w,v,w.length,4,num));
        System.out.println( longestCommonPrefix(new String[]{"abc","abd","ab"}));
        longestCommonPrefix(new String[]{"abc","abcd","abcd","a"});
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
     * 01背包问题
     * @param w
     * @param v
     * @return
     */
   public static int package01(int[] w,int[] v,int n,int m){
       //表示前i(可以表示为物品的标号，数量可以不为1)种物品恰好放入重量为m的背包时的最大价值
       int[][] dp=new int[n+1][m+1];

       //初始化数组
       for(int i=0;i<n+1;i++){
           dp[i][0] = 0;
       }
       for(int j=0;j<m+1;j++){
           dp[0][j] = 0;
       }

        //物品的编号
       for(int i=1;i<n+1;i++){
           //背包的最大容量
           for (int j=1;j<m+1;j++){
               //物品的重量小于背包的容量
               if (w[i-1] <= j){
                   //加入物品 和没有放入背包之前做比较
                   dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-w[i-1]]+v[i-1]);
               } else{
                   //该物品i不能放入，只能保持原来的物品数量的价值
                   dp[i][j]=dp[i-1][j];
               }
           }
       }
     return dp[n][m];
   }

   /**
    * f[i][v]=max{f[i-1][v-k*c[i]]+k*w[i]|0<=k*c[i]<=v} 物品可以加多次
     * 完全背包问题(每个物品不限量)
     * @param w
     * @param v
     * @return
     */
   public static int packageFull(int[] w,int[] v,int n,int m){
       //表示前i件物品恰好放入重量为m的背包时的最大价值
       int[][] dp=new int[n+1][m+1];

       //初始化数组
       for(int i=0;i<n+1;i++){
           dp[i][0] = 0;
       }
       for(int j=0;j<m+1;j++){
           dp[0][j] = 0;
       }

        //物品的编号
       for(int i=1;i<n+1;i++){
           //背包的最大容量
           for (int j=1;j<m+1;j++){
               //物品的重量小于背包的容量
               for (int k=0;k*w[i-1]<= j;k++){
                   //里面包含可以放置0到n个的所有情况
                   dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-k*w[i-1]]+k*v[i-1]);
               }

           }
       }
     return dp[n][m];
   }


   /**
    *
    * 多重背包问题（加上物品的数量限制）
    * f[i][v]=max{f[i-1][v-k*c[i]]+k*w[i]|0<=k*c[i]<=v} 物品可以加多次
     * 完全背包问题(每个物品不限量)
     * @param w
     * @param v
     * @return
     */
   public static int packageMany(int[] w,int[] v,int n,int m,int[] nums){
       //表示前i件物品恰好放入重量为m的背包时的最大价值
       int[][] dp=new int[n+1][m+1];

       //初始化数组
       for(int i=0;i<n+1;i++){
           dp[i][0] = 0;
       }
       for(int j=0;j<m+1;j++){
           dp[0][j] = 0;
       }
        //物品的编号
       for(int i=1;i<n+1;i++){
           //背包的最大容量
           for (int j=1;j<m+1;j++){
               //物品的重量小于背包的容量

               //背包数量限制
               for (int k=0; k<=nums[i-1];k++){
                   //多个物品小于背包的数量，可以申请加入
                   if (k*w[i-1] <= j){
                       dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-k*w[i-1]]+k*v[i-1]);
                   }
               }

           }
       }
     return dp[n][m];
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


    public static String longestCommonPrefix(String[] data){
        String common=data[0];
        for (int i=1;i<data.length;i++){
            String temp=data[i];
            common=getCommon(common,temp);
        }
        return common;
    }

    private static String getCommon(String common, String temp) {
        byte[] bytes = common.getBytes();
        byte[] bytes1 = temp.getBytes();
        int commonIndex=0;
        for (int i=0;i<Math.min(bytes.length,bytes1.length);i++){
            if ( bytes[i] != bytes1[i]){
                break;
            }
            if (bytes[i] == bytes1[i]){
                commonIndex=i;
            }
        }
        return common.substring(0,commonIndex+1);
    }



    /**
     *给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     *
     * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     * @Author shuxibing
     * @UpdateDate 2020/9/7 14:51
     * @Uint d9lab
     * @Description:  状态方程的变化
     *
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n=triangle.size();
       int[][] dp=new int[n][n];
       dp[0][0]=triangle.get(0).get(0);
        for (int i=1;i<n;++i){
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            for (int j=1;j<i;++j){
                dp[i][j]=Math.min(dp[i-1][j-1],dp[i-1][j])+triangle.get(i).get(j);
            }
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }
        //找出最小值
        int min=dp[n-1][0];
        for (int k=1;k<n;k++){
           min=Math.min(min,dp[n-1][k]);
        }
        return min;
    }


    /**
     *有路障的情况下，路线设置问题
     * @Author shuxibing
     * @UpdateDate 2020/9/7 17:51
     * @Uint d9lab
     * @Description:
     *
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp=new int[obstacleGrid.length][obstacleGrid[0].length];
        if (obstacleGrid[0][0] ==1){
            dp[0][0]=0;
        }else {
            dp[0][0]=1;
        }
        //初始化路径数据
        for (int j=1;j<obstacleGrid[0].length;j++){
            if (obstacleGrid[0][j]==1){
                dp[0][j]=0;
            }else {
                dp[0][j]=dp[0][j-1];
            }
        }
        for (int i=1;i<obstacleGrid.length;i++){
            if (obstacleGrid[i][0]==1){
                dp[i][0]=0;
            }else {
                dp[i][0]=dp[i-1][0];
            }
        }


        for(int i=1;i<obstacleGrid.length;i++){
            for (int j=1;j<obstacleGrid[i].length;j++){
                //此路不通直接设置0
                if (obstacleGrid[i][j] == 1){
                    dp[i][j]=0;
                }else {
                    dp[i][j]=dp[i][j-1]+dp[i-1][j];
                }
            }
        }

        return dp[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }



    public int minPathSum(int[][] grid) {
        int[][] dp=new int[grid.length][grid[0].length];

        dp[0][0]=grid[0][0];
        for (int i=1;i<grid.length;i++){
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }

        for (int j=1;j<grid[0].length;j++){
            dp[0][j]=dp[0][j-1]+grid[0][j];
        }

        for (int i=1; i<grid.length;i++){
            for (int j=1;j<grid[0].length;j++){
                dp[i][j]=Math.min(dp[i][j-1],dp[i-1][j])+grid[i][j];
            }
        }

        return dp[grid.length-1][grid[0].length-1];
    }

}
