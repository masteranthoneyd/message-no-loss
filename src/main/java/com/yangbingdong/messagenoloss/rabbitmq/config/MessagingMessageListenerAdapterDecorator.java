package com.yangbingdong.messagenoloss.rabbitmq.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * @author ybd
 * @date 2020/4/23
 * @contact yangbingdong1994@gmail.com 装饰器
 */
public class MessagingMessageListenerAdapterDecorator implements ChannelAwareMessageListener {
    public static final ThreadLocal<Channel> THREAD_LOCAL = new ThreadLocal<>();
    private final MessagingMessageListenerAdapter messageListener;

    public MessagingMessageListenerAdapterDecorator(MessagingMessageListenerAdapter messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void onMessage(Message amqpMessage, Channel channel) throws Exception {
        try {
            THREAD_LOCAL.set(channel);
            messageListener.onMessage(amqpMessage, channel);
        } finally {
            THREAD_LOCAL.remove();
        }
    }
}
