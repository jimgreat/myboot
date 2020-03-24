package com.netty.protocol;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> target;

    public RpcEncoder(Class target) {
        this.target = target;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) throws Exception {
        if(target.isInstance(msg)){
            byte[] data = JSON.toJSONBytes(msg); //使用fastJson将对象转换为byte
            byteBuf.writeInt(data.length); //先将消息长度写入，也就是消息头
            byteBuf.writeBytes(data); //消息体中包含我们要发送的数据
        }
    }
}
