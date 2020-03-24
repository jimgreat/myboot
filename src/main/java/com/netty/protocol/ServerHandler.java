package com.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger counter = new AtomicInteger();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            RpcRequest request = (RpcRequest) msg;
            System.out.println("接收到客户端信息:" + request.toString());
            RpcResponse response = new RpcResponse();
            response.setId(UUID.randomUUID().toString());
            response.setData("server响应结果");
            response.setStatus(counter.incrementAndGet());
            ctx.writeAndFlush(response);
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    //通知处理器最后的channelRead()是当前批处理中的最后一条消息时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收数据完毕..");
        ctx.flush();
    }

    //读操作时捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    //客户端去和服务端连接成功时触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        System.out.println("客户端去和服务端连接成功时触发");
    }
}
