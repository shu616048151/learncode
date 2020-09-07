package com.shu.alogrithm.mianshi;

import com.hankcs.hanlp.classification.features.DfFeatureData;
import org.junit.Test;

import java.util.*;

/**
 * @Author shuxibing
 * @Date 2020/9/6 10:12
 * @Uint d9lab_2019
 * @Description:
 */
public class Meituan {
//    public static void main(String[] args){
//        Scanner scanner=new Scanner(System.in);
//        String str1 = scanner.nextLine();
//        String strA = scanner.nextLine();
//        String strB = scanner.nextLine();
//        String[] data = strA.split(" ");
//        String[] dataB = strB.split(" ");
//        int A=0;
//        int B=0;
//        int common=0;
//        for (int i=0;i<data.length;i++){
//            String datum = data[i];
//            if (strB.indexOf(datum) != -1){
//                common++;
//            }else {
//                A++;
//            }
//        }
//        System.out.println(A);
//        System.out.println(dataB.length-common);
//        System.out.println(common);
//
//    }
//
//    public static void main(String[] args){
//        Scanner scanner=new Scanner(System.in);
//        String str1 = scanner.nextLine();
//        String str2 = scanner.nextLine();
//        Integer n=Integer.valueOf(str1);
//        String[] dataB = str2.split(" ");
//        int[] a=new int[n];
//        for (int i=0;i<dataB.length;i++){
//            a[i]=Integer.valueOf(dataB[i]);
//        }
//        int out= getB(a[0],  1,n);
//        for (int i=1;i<n;i++){
//            int b = getB(a[i], i + 1,n);
//            System.out.println("nnn:"+b);
//            out=out^b;
//        }
//        System.out.println(out);
//
//    }

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
//        String str1 = scanner.nextLine();
//        String str2 = scanner.nextLine();
//        String str3 = scanner.nextLine();

//        String str1 = "5";
//        String str2 = "0 1 0 0 0 0 0 1 1 0 1 0 0 0 0 0 0 0 0 1 0 1 1 0 0";
//        String str3 = "4";

        String str1 = "6";
        String str2 = "0 1 1 1 0 1 1 0 0 0 0 1 0 0 0 1 1 1 0 1 0 0 1 1 0 0 0 0 0 0 0 1 0 0 1 0";
        String str3 = "3";
        Integer n=Integer.valueOf(str1);
        Integer start=Integer.valueOf(str3);
        int[][] data=new int[n][n];
        String[] strdata = str2.split(" ");
        for (int i=0;i<strdata.length;i++){
            int x=i/n;
            int y=i%n;
            data[x][y]=Integer.valueOf(strdata[i]);
        }
        LinkedList<Integer> list = new LinkedList<>();
        list.add(start);
        dfs(data,start,list,new ArrayList<>());
    }

    public static void count(String strA,String strB){
        String[] data = strA.split(" ");
        String[] dataB = strB.split(" ");
        int A=0;
        int B=0;
        int common=0;
        for (int i=0;i<data.length;i++){
            String datum = data[i];
            if (strB.indexOf(datum) != -1){
                common++;
            }else {
                A++;
            }
        }
        System.out.println(A);
        System.out.println(dataB.length-common);
        System.out.println(common);
    }

    @Test
    public void test(){
        count("1 2 3","3 4 5");
    }


    public static int getB(int a,int k,int n){
        int b=a;
        for(int i=1; i<n+1;i++){
            int temp=k%i;
            b=b^temp;
        }
        return b;
    }


    //回溯算法
    public static void dfs(int[][] data, int start, List<Integer> list, ArrayList<String> out){
        for (int i=0;i<data[start].length;i++){
            if (data[start][i] == 1){
                //边界条件
                if(i == list.get(0)){
//                    System.out.println(list.get(0));
                    //形成环路
                    String str="";
                    for (Integer num:list){
                        str+=num;
                    }
                    System.out.println(str);
                    out.add(str);
                    return;
                }
                if (list.contains(i)){
                        continue;
                }
                list.add(i);
//                System.out.println("添加："+i);
                dfs(data,i,list,out);
//                System.out.println("去除："+list.get(list.size()-1));
                list.remove(list.size()-1);
            }
        }
    }


}
