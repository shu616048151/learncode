package com.shu.fastdfs.test;

import java.io.*;

/**
 * @author shuxibing
 * @date 2019/11/14 10:19
 * @uint d9lab
 * @Description:
 */
public class FileTest4 {
    public static void main(String[] args) throws IOException {

        FileReader fileReader=new FileReader("c:\\Users\\Administrator\\Desktop\\imagesize.txt");
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        double count=0.0;
        String path="";
        int i=0;
        while ((path=bufferedReader.readLine())!=null){
            if (path.endsWith("MB")){
                i++;
                System.out.println(path);
                double temp=Double.valueOf(path.substring(path.lastIndexOf("-")+1,path.lastIndexOf("MB")));
                System.out.println("第"+i+"  "+temp);
                count+=temp;
    //            if (i>100){
    //                break;
    //            }
            }
        }
        System.out.println("总存储空间："+(count)+"MB");
        System.out.println("总存储空间："+(count/1024)+"GB");
        bufferedReader.close();
        fileReader.close();
    }
}
