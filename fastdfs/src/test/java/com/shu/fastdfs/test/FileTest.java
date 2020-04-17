package com.shu.fastdfs.test;

import com.shu.fastdfs.imagesync.ImageDao;
import com.shu.fastdfs.util.FileUtil;
import org.junit.Test;

import java.io.File;

/**
 * @author shuxibing
 * @date 2019/11/11 17:24
 * @uint d9lab
 * @Description:
 */
public class FileTest {
    public static void main(String[] args){
        String path=" https://face.ceks100.com/group1/M0B/00/00/rBggYV3GnAiALuB3AEq-uhr1Mm0906.jpg";
//        String linuxPath="/alfa/whut/data11/fastdfs/data/00/00/rBggYV3GnAiALuB3AEq-uhr1Mm0906.jpg";
        String linuxPath="c:\\Users\\Administrator\\Desktop\\bos.sql";
        File file=new File(linuxPath);
        if (file.exists() && file.isFile()){
            System.out.println(file.length());
        }



    }

    @Test
    public void test() throws Exception {
        File file=new File("C:\\Users\\Administrator\\Desktop\\3.jpg");
        String upload = FileUtil.upload("1", file);
        ImageDao imageDao=new ImageDao();
        //imageDao.insert(upload);
        System.out.println(upload);
    }
    @Test
    public void testMovie() throws Exception {
        File file=new File("D:\\BaiduYunDownload\\微信公众号 互联网安全技术 推荐系统算法工程师——从入门到就业\\[365cmd.com]2_推荐系统的深度学习应用之IMDb解析.mp4");
        String upload = FileUtil.upload("1", file);
        ImageDao imageDao=new ImageDao();
        imageDao.insert(upload);
        System.out.println(upload);
    }

}
