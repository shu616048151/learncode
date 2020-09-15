package com.shu.alogrithm;

/**
 * @Author shuxibing
 * @Date 2020/9/14 20:19
 * @Uint d9lab_2019
 * @Description:
 */
public class Main {
    public static void main(String[] args){

    }

    public int compute(int x,int y,int z,int[] data){
        int tai=x+y+z;
        int sum=0;
        for (int i=0;i< data.length;i++){
            sum+=data[i];
        }
        int maxTai=sum/tai;

        return 0;
    }
}
