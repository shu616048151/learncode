package com.shu.alogrithm;

import java.util.*;

/**
 * @Author shuxibing
 * @Date 2020/4/4 15:48
 * @Uint d9lab_2019
 * @Description:
 */
public class Test {


    public static String countandSay(int n){
        String[] str=new String[n];
        str[0]="1";
        for (int i=1;i<n;i++){
            String s = str[i - 1];
            String newStr="";
            char[] chars = s.toCharArray();
            int countlength=1;
            for (int j=0;j<chars.length;j++){
                if (j<chars.length-1&&chars[j]==chars[j+1]){
                    countlength++;
                    continue;
                }else{
                    if (countlength==1){
                        newStr=newStr+"1"+chars[j];
                    }else {
                        newStr=newStr+countlength+chars[j];
                        countlength=1;
                    }

                }
            }
            str[i]=newStr;
        }
        return str[n-1];
    }

    @org.junit.Test
    public void test(){
        System.out.println(Integer.toBinaryString(964176192));
    }

    public static int hammingWeight(int n) {
        System.out.println(n);
        int count=0;
        String s = ""+n;
        System.out.println(s);
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar==1){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args){
        System.out.println(hammingWeight(00000000000000000000000000001011));
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length==0||nums.length==1){
            return nums.length;
        }
        Arrays.sort(nums);
        int max=1;
        int temp=1;
        for (int i=1;i<nums.length;i++){
            if (nums[i]!=nums[i-1]){
                if (nums[i]-nums[i-1]==1){
                    temp+=1;
                }else {
                    max=Math.max(max,temp);
                    temp=1;
                }
            }

        }
        return max;

    }


}
