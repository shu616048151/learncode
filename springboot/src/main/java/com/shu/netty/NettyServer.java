package com.shu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.util.Date;

/**
 * @author shuxibing
 * @date 2019/8/8 6:53
 * @uint d9lab
 * @Description:
 */
public class NettyServer {
    public static void main(String[] args){
        ServerBootstrap bootstrap=new ServerBootstrap();
        NioEventLoopGroup boss=new NioEventLoopGroup();
        NioEventLoopGroup worker=new NioEventLoopGroup();

        bootstrap.group(boss,worker).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {

            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                nioSocketChannel.pipeline().addLast(new StringDecoder());
                nioSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
                        System.out.println(msg);
                    }

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        super.channelActive(ctx);
                        ctx.writeAndFlush("服务端发送数据："+new Date());


                    }
                });
            }
        }).bind(8000);

    }
}
