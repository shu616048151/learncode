package com.shu.base.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author shuxibing
 * @date 2019/5/17 0017 15:28
 * @uint d9lab
 * @Description:
 */
public class TestChannelRead {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\foo.txt");
        // 获取通道
        FileChannel fileChannel = fileInputStream.getChannel();


        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取数据到缓冲区
        try {
            fileChannel.read(buffer);
            System.out.println(fileChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 重设buffer，将limit设置为position，position设置为0
        buffer.flip();

        // 查看在position和limit之间是否有元素
        while (buffer.hasRemaining()) {
            // 读取buffer当前位置的整数
            byte b = buffer.get();
            System.out.print((char) b);
        }

        fileInputStream.close();
    }


}
