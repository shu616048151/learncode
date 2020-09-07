package com.shu.alogrithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author shuxibing
 * @Date 2020/9/7 15:29
 * @Uint d9lab_2019
 * @Description:
 */
public class NewTest {

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        Integer num = Integer.valueOf(scanner.nextLine());
        for (int i=0;i<num;i++){
            String target=scanner.nextLine();
            String out="";
            for (int j=0;j<target.length();j++){
                char c = target.charAt(j);
                if (c == '1'){
                    out+="63231323";
                }
                if (c == '2'){
                    out+="53231323";
                }
                if (c == '3'){
                    out+="43131323";
                }

            }
//            System.out.println(out);
            String str=scanner.nextLine();
//            String str="666665323132353231323111111111";
            count(out,str);
        }

    }
    public static void count(String target,String str){
        int count=0;
        while (str.indexOf(target) != -1){
            count++;
            if (target.length() == 8){
                str=str.substring(str.indexOf(target)+target.length());
            }else {
                str=str.substring(str.indexOf(target)+target.length()-8);
            }
//            System.out.println(str);
        }
        System.out.println(count);
    }
}
