package com.yangbingdong.messagenoloss.rabbitmq.config;

import org.springframework.amqp.rabbit.config.ContainerCustomizer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter;

/**
 * @author ybd
 * @date 2020/4/23
 * @contact yangbingdong1994@gmail.com
 */
public class RabbitContainerCustomizer implements ContainerCustomizer<SimpleMessageListenerContainer> {
    @Override
    public void configure(SimpleMessageListenerContainer container) {
        container.setMessageListener(new MessagingMessageListenerAdapterDecorator((MessagingMessageListenerAdapter) container.getMessageListener()));
    }
}
