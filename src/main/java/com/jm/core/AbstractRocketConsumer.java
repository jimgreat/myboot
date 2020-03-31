package com.jm.core;

import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

public abstract class AbstractRocketConsumer implements RocketConsumer {

    public String topics;
    public String tags;
    public MessageListenerConcurrently messageListenerConcurrently;
    public String consumerTitel;
    public MQPushConsumer mqPushConsumer;

    public void necessary(String topics, String tags, String consumerTitel) {
        this.topics = topics;
        this.tags = tags;
        this.consumerTitel = consumerTitel;
    }

    @Override
    public abstract void init();

    @Override
    public void registerMessageListener(MessageListenerConcurrently messageListenerConcurrently) {
        this.messageListenerConcurrently = messageListenerConcurrently;
    }
}
