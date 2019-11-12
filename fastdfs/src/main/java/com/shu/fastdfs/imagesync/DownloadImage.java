package com.shu.fastdfs.imagesync;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author shuxibing
 * @date 2019/11/11 14:53
 * @uint d9lab
 * @Description:
 */
public class DownloadImage {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String imageUrl="https://face.ceks100.com/group1/M01/8D/AF/cx2hu1mznHeAPJ3ZAEuAf3ewJBg134.jpg";
        String path=imageUrl.substring(imageUrl.indexOf("group"),imageUrl.lastIndexOf("/"));
        System.out.println(path);
        String fileName=imageUrl.substring(imageUrl.lastIndexOf("/")+1);
        System.out.println(fileName);
        download(imageUrl, fileName,"d:\\image\\"+path);
        System.out.println("下载完成");
    }

    public static void download(String urlString, String filename,String savePath) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(100*1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

}
