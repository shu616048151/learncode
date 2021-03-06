package com.shu.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author shuxibing
 * @date 2019/10/22 17:16
 * @uint d9lab
 * @Description:
 */
public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap(); // 创建 Bootstrap
            bootstrap.group(group) // 指定EventLoopGroup来处理客户端事件。由于我们使用NIO传输，所以用到了 NioEventLoopGroup 的实现
                    .channel(NioSocketChannel.class) // 使用的channel类型是一个用于NIO传输
                    .remoteAddress(new InetSocketAddress(host, port)) // 设置服务器的InetSocketAddr
                    .handler(new ChannelInitializer<SocketChannel>() { // 当建立一个连接和一个新的通道时。创建添加到EchoClientHandler实例到 channel pipeline
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //按顺序执行各种处理的handler
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect().sync(); // 连接到远程；等待连接完成

            future.channel().closeFuture().sync(); // 阻塞到远程; 等待连接完成
        } finally {
            group.shutdownGracefully().sync(); // 关闭线程池和释放所有资源
        }
    }

    public static void main(String[] args) throws Exception {
        final String host = "127.0.0.1";
        final int port = 4567;
        new EchoClient(host, port).start();
    }
}