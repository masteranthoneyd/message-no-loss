package com.yangbingdong.messagenoloss.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static java.lang.Boolean.parseBoolean;

/**
 * @author ybd
 * @date 2020/4/23
 * @contact yangbingdong1994@gmail.com
 */
@Configuration
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:MQ-CONF/topic.yml")
@EnableConfigurationProperties(MessagingProperty.class)
public class MessagingConfiguration implements InitializingBean {

    private final MessagingProperty messagingProperty;
    private final RabbitAdmin rabbitAdmin;

    public MessagingConfiguration(MessagingProperty messagingProperty, RabbitAdmin rabbitAdmin) {
        this.messagingProperty = messagingProperty;
        this.rabbitAdmin = rabbitAdmin;
    }

    @Override
    public void afterPropertiesSet() {
        if (MqType.RABBIT.equals(messagingProperty.getMqType())) {
            registryRabbit(messagingProperty);
        }
    }

    private void registryRabbit(MessagingProperty messagingProperty) {
        for (MessagingProperty.Topic topic : messagingProperty.getTopics()) {
            String topicName = topic.getName();
            Queue queue = new Queue(topicName, true, false, false);
            DirectExchange exchange = new DirectExchange(topicName, true, false);
            exchange.setDelayed(parseBoolean(topic.getProperties().getOrDefault("delayed", "false")));
            Binding binding = BindingBuilder.bind(queue).to(exchange).with(topicName);
            rabbitAdmin.declareQueue(queue);
            rabbitAdmin.declareExchange(exchange);
            rabbitAdmin.declareBinding(binding);
        }
    }
}
