package com.netty.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

public class RpcNettyClient {

    public static void main(String[] args) throws Exception {
        RpcNettyClient client = new RpcNettyClient("127.0.0.1",8000);
        client.start();
        Channel channel = client.getChannel();
        while(true) {
            RpcRequest request = new RpcRequest();
            request.setId(UUID.randomUUID().toString());
            request.setData("client.message");
            channel.writeAndFlush(request);
            Thread.sleep(2000);
        }
    }

    private final String host;
    private final int port;
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    //连接服务端的端口号地址和端口号
    public RpcNettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception{
        final EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("正在连接中...");
                        ch.pipeline()
                                .addLast(new RpcEncoder(RpcRequest.class))
                                .addLast(new RpcDecoder(RpcResponse.class))
                                .addLast(new ClientHandler());
                    }
                });

        //发起异步连接请求，绑定连接端口和host信息
        final ChannelFuture future = b.connect(host, port).sync();

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("连接服务器成功");
                } else {
                    System.out.println("连接服务器失败");
                    future.cause().printStackTrace();
                    group.shutdownGracefully(); //关闭线程组
                }
            }
        });

        this.channel = future.channel();
    }
}
