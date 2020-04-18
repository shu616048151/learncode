package com.springboot.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Author shuxibing
 * @Date 2019/5/17 0017 18:57
 * @uint d9lab
 * @Description:
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }


    public void start() throws InterruptedException {
        NioEventLoopGroup group=new NioEventLoopGroup();

        ServerBootstrap bootstrap=new ServerBootstrap();
        try {
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //初始化channel
                        socketChannel.pipeline().addLast(new EchoServerHandler());
                    }
                });

            ChannelFuture future = bootstrap.bind().sync(); // 绑定的服务器;sync 等待服务器关闭
            System.out.println(EchoServer.class.getName() + " started and listen on " + future.channel().localAddress());
            future.channel().closeFuture().sync(); // 关闭 channel 和 块，直到它被关闭

        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 4567;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        new EchoServer(port).start(); // 设计端口、启动服务器
    }
}
