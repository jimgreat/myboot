package com.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class NettyServer {
    public static void main(String[] args) throws Exception{
        System.out.println("Start NettyServer");
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childOption(ChannelOption.TCP_NODELAY,true)

                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new StringDecoder())
                                .addLast(new IdleStateHandler(2,2,2,TimeUnit.SECONDS))
                                .addLast(new SimpleChannelInboundHandler<String>() {
                                    @Override
                                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                        System.out.println("Got an exec!"+cause.getMessage());
                                    }

                                    @Override
                                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                        System.out.println(s);
                                    }

                                    @Override
                                    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                        IdleStateEvent event = (IdleStateEvent)evt;
                                        String eventType = null;
                                        switch (event.state()){
                                            case READER_IDLE:
                                                eventType = "ReadIdel";
                                                break;
                                            case WRITER_IDLE:
                                                eventType = "WriteIdel";
                                                break;
                                            case ALL_IDLE:
                                                eventType ="AllIdel";
                                                break;
                                        }

                                        System.out.println(ctx.channel().remoteAddress()+" "+eventType);
                                    }
                                });
                    }
                });

                ChannelFuture future = serverBootstrap.bind(8000).sync();
                future.addListener(f -> {
                    if(f.isSuccess()){
                        System.out.println("bind Success");
                    }
                });

                future.channel().closeFuture().sync();
                System.out.println("End");
    }
}
