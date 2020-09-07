package com.shu.alogrithm.mianshi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author shuxibing
 * @Date 2020/3/23 21:41
 * @Uint d9lab_2019
 * @Description: 华为五福机试
 */
public class HuaWei20200401 {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String line = scanner.nextLine();
        String[] split = line.split(",");
        int i = countWFNum(split);
        System.out.println(i);
    }


    public static int countWFNum(String[] arr){
        int[] count={0,0,0,0,0};
        for (int i=0;i<arr.length;i++){
            String s = arr[i];
            char[] chars = s.toCharArray();
            for (int j=0;j<chars.length;j++){
                if (chars[j]=='1'){
                    count[j]=count[j]+1;
                }
            }
        }
        int min=Integer.MAX_VALUE;
        for (int k=0;k<count.length;k++){
            if (count[k]<min){
                min=count[k];
            }
        }
        return min;
    }




    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<num.length-size+1;i++){
            int max=num[i];
            for(int j=i;j<i+size;j++){
                if(num[j]>max){
                    max=num[j];
                }
            }
            list.add(max);
        }
        return list;
    }
}
