package com.shu.alogrithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @Author shuxibing
 * @Date 2020/9/12 15:26
 * @Uint d9lab_2019
 * @Description:
 */
public class Test2020 {


    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String s = scanner.nextLine();
        String[] s1 = s.split(" ");
        int[] data=new int[s1.length];
        for (int i=0;i<data.length;i++){
            data[i]=Integer.valueOf(s1[i]);
        }
        int out = maxSumDivSeven(data);
        System.out.println(out == 0?-1:out);
    }

//    @Test
//    public void test(){
//        int[] data={1,3,4,7};
//        System.out.println(maxSumDivSeven(data));
//    }


    public static int maxSumDivSeven(int[] data){
        //记录被7整除余数的为i的总和最大值
        int[] dp = {0,Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int i=0; i< data.length;i++){
            int[] temp=new int[7];
            int mod=data[i]%7;
            for(int j=0;j<7;++j){
                //j-mod表示找到余数为的最大值
                temp[j]=Math.max(dp[j],dp[(7+j-mod)%7]+data[i]);
            }
            dp=temp;

        }
        return dp[0];
    }

}
