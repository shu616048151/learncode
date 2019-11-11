package com.shu.fastdfs.test;

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

    }

    @Test
    public void test() throws Exception {
        File file=new File("C:\\Users\\Administrator\\Desktop\\3.jpg");
        String upload = FileUtil.upload("1", file);
        System.out.println(upload);
    }
}
