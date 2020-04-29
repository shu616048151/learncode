package com.shu.alogrithm;

import java.util.Scanner;

/**
 * @Author shuxibing
 * @Date 2020/4/25 14:27
 * @Uint d9lab_2019
 * @Description:
 */
public class Zhaoyin {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String str = scanner.nextLine();
        StringBuilder s1=new StringBuilder();
        StringBuilder s2=new StringBuilder();
        if (str!=null){
            char[] chars = str.toCharArray();
            for (char c : chars) {
                if (c=='*'){
                    s1.append(c);
                }else {
                    s2.append(c);
                }
            }
        }
        System.out.println(s1.toString()+s2.toString());
    }
}
