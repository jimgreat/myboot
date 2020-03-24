package com.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

public class HttpNettyServer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss,worker)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new HttpServerCodec())
                                    .addLast("httpAggregator",new HttpObjectAggregator(512*1024))
                                    .addLast(new SimpleChannelInboundHandler<FullHttpRequest>() {

                                        @Override
                                        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                            ctx.flush();
                                        }

                                        @Override
                                        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {

                                            // 获取请求的uri
                                            String uri = req.uri();
                                            Map<String,String> resMap = new HashMap<>();
                                            resMap.put("method",req.method().name());
                                            resMap.put("uri",uri);
                                            String msg = "<html><head><title>test</title></head><body>你请求uri为：" + uri+"</body></html>";
                                            // 创建http响应
                                            FullHttpResponse response = new DefaultFullHttpResponse(
                                                    HttpVersion.HTTP_1_1,
                                                    HttpResponseStatus.OK,
                                                    Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
                                            // 设置头信息
                                            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
                                            //response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
                                            // 将html write到客户端
                                            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

                                        }
                                    });
                        }
                    });
            ChannelFuture future = b.bind(8000).sync();
            future.addListener(f->{
                if(f.isSuccess()){
                    System.out.println("Success");
                }
            });
            future.channel().closeFuture().sync();
        }catch (Exception e){
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
