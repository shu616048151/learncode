package com.shu.fastdfs.test;

import java.io.File;
import java.math.BigDecimal;

/**
 * @author shuxibing
 * @date 2019/11/13 21:03
 * @uint d9lab
 * @Description:
 */
public class FileTest1 {
    public static void main(String[] args){
        String path=" https://face.ceks100.com/group1/M0B/00/00/rBggYV3GnAiALuB3AEq-uhr1Mm0906.jpg";
        String linuxPath="/alfa/whut/data11/fastdfs/data/00/00/rBggYV3GnAiALuB3AEq-uhr1Mm0906.jpg";
//        String linuxPath="D:\\BaiduYunDownload\\微信公众号 互联网安全技术 推荐系统算法工程师——从入门到就业\\[365cmd.com]1_深度学习在推荐系统中的应用.mp4";
        File file=new File(linuxPath);
        if (file.exists() && file.isFile()){
            long length = file.length();
            System.out.println("字节数:"+length);
            System.out.println(getPrintSize(length));
        }
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
            return String.valueOf(value) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            return String.valueOf(value) + "GB";
        }
    }


}
