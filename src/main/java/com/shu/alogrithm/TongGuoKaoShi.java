package com.shu.alogrithm;

import java.util.Scanner;

/**
 * @author shuxibing
 * @date 2019/12/13 21:47
 * @uint d9lab
 * @Description:
 *
 * 题目描述：小明同学要参加一场考试，考试一共有n道题目，小明必须做对至少60%的题目才能通过考试。考试结束后，小明估算出每题做对的概率，p1,p2,…,pn。你能帮他算出他通过考试的概率吗？
 *
 * 输入：
 * 第一行一个数n（1<=n<=100），表示题目的个数。
 *
 * 第二行n个整数，p1,p2,…,pn。表示小明有pi%的概率做对第i题。（0<=pi<=100）
 *
 * 样例输入：
 *
 * 4
 *
 * 50 50 50 50
 *
 * 输出：小明通过考试的概率，最后结果四舍五入，保留小数点后五位。
 *
 * 样例输出：0.3125
 */
public class TongGuoKaoShi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        double[] p = new double[num];
        int t = 0;
        while (t < num){
            p[t] = scanner.nextDouble() / 100;
            t++;
        }
        chakan(p,num);

    }

    /**
     * 基本思路：记录i道做对j道概率，分开来计算通过的概率
     * @param p
     * @param num
     */
    public static void chakan(double[] p, int num){
        double[][] dp = new double[num+1][num+1];
        dp[0][0] = 1;
        //行是通过题的数量，列是题的个数
        for (int i = 1; i <= num; i++){
            //初始化第一列，dp[0][0]是0道题作对0道的情况，dp[1][0]是1道题作对0道的情况
            //对于前面i-1道题，作对0道题，同时第i道做错，
            // 概率为 dp[0][0] * (1 - p[0]) <==> dp[i-1][j] * (1-p[i-1])
            // (也就是前面i-1道通过j道题的概率 * 第i道没通过的概率)
            dp[i][0] = dp[i-1][0] * (1 - p[i-1]);
            for (int j = 1; j <= num; j++){ //通过i道的答题情况
                //dp[i-1][j-1] * p[i-1] 前i-1道作对j-1道的概率 * 当前i这个题的概率
                dp[i][j] = dp[i-1][j-1] * p[i-1] + dp[i-1][j]*(1-p[i-1]);
            }
        }
        double pass = 0.0d;
        int i = (int) Math.ceil(num*0.6);//需要做对的题目数
        for ( ; i <= num; i++){
            pass += dp[num][i];//最后一行，大于要答对题目的概率的总和
        }
        System.out.println(pass);
    }
}
