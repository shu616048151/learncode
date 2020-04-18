package com.shu.fastdfs.test;


import java.io.*;
import java.math.BigDecimal;

/**
 * @author shuxibing
 * @date 2019/11/14 8:57
 * @uint d9lab
 * @Description:
 */
public class FileTest2 {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("/alfa/tmp/imagefile.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linuxPath = "";
        FileWriter fileWriter=new FileWriter("/alfa/tmp/imagesize.txt");
        int num=0;
        File file=null;
        while ((linuxPath = bufferedReader.readLine())!=null){
             file = new File(linuxPath);
            if (file.exists() && file.isFile()) {
                num++;
                long length = file.length();
                System.out.println("第"+num+"张  字节数:" + length+"  "+ linuxPath);
                String printSize = getPrintSize(length);
                System.out.println(printSize+"MB");
                fileWriter.write(linuxPath+"-"+printSize+"");
                fileWriter.write("\n");
            }
        }
        fileReader.close();
        fileWriter.close();
    }

    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        double value = (double) size;
        if (value < 1024) {
            return String.valueOf(value) + "B";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (value < 1024) {
            return String.valueOf(value) + "KB";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        if (value < 1024) {
            return String.valueOf(value);
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            return String.valueOf(value) + "GB";
        }
    }
}
