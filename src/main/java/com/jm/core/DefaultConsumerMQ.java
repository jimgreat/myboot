package com.jm.core;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultConsumerMQ extends AbstractRocketConsumer {
    @Override
    public void init() {

        super.necessary("TopicTest", "*", "这是标题");

        registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                msgs.forEach(msg -> {
                    System.out.printf("consumer message boyd %s %n", new String(msg.getBody()));
                });
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }
}
