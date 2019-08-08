package com.shu.base.nio.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author shuxibing
 * @date 2019/8/7 16:08
 * @uint d9lab
 * @Description:
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        Selector serverSelector=Selector.open();
        Selector clientSelector=Selector.open();
        new Thread(()->{
            try {
                ServerSocketChannel listenChannel=ServerSocketChannel.open();
                listenChannel.socket().bind(new InetSocketAddress(8080));
                listenChannel.configureBlocking(false);
                listenChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
                while (true){
                    if (serverSelector.select(1)>0){
                        Set<SelectionKey> keys=serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator=keys.iterator();
                        while (keyIterator.hasNext()){
                            SelectionKey key=keyIterator.next();
                            if (key.isAcceptable()){
                                //对于新线程进行连接,绑定到新线程
                                try {
                                    SocketChannel clinetChannel=((ServerSocketChannel)(key.channel())).accept();
                                    clinetChannel.configureBlocking(false);
                                    clinetChannel.register(clientSelector,SelectionKey.OP_ACCEPT);
                                }catch (IOException e){

                                }
                            }
                        }
                    }
                }


            }catch (Exception e){

            }
        }).start();

        //批量处理线程
        new Thread(()->{
            try {
                while (true){
                    if (clientSelector.select(1)>0){
                        Set<SelectionKey> keys = clientSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = keys.iterator();
                        while (keyIterator.hasNext()){
                            SelectionKey key = keyIterator.next();
                            //准备完成可以读写
                            if (key.isReadable()){
                                SocketChannel socketChannel=(SocketChannel)key.channel();
                                //使用缓存区进行存储
                                ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                                socketChannel.read(byteBuffer);
                                byteBuffer.flip();
                                System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());

                            }
                        }
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }
}
