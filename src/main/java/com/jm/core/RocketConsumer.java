package com.jm.core;

import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

public interface RocketConsumer {
    /**
     * 初始化消费者
     */
    public abstract void init();

    /**
     * 注册监听
     *
     * @param messageListenerConcurrently
     */
    public void registerMessageListener(MessageListenerConcurrently messageListenerConcurrently);
}
