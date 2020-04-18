package com.shu.base.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author shuxibing
 * @date 2019/5/17 0017 15:48
 * @uint d9lab
 * @Description:
 */
public class FileIO {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream=new FileInputStream("C:\\Users\\Administrator\\Desktop\\foo.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("C:\\Users\\Administrator\\Desktop\\foo1.txt",true);
        FileChannel channel = fileInputStream.getChannel();
        FileChannel ochannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        while (true){
            //清空缓存区
            byteBuffer.clear();
            //开始读取到缓存区
            int r = channel.read(byteBuffer);
            if (r==-1){
                break;
            }
            //重新设置position，使得能够从头开始读取文件,并写入输出文件
            byteBuffer.flip();
            ochannel.write(byteBuffer);
        }
        ochannel.close();
        channel.close();
        fileInputStream.close();
        fileOutputStream.close();

    }
}
