package com.yangbingdong.messagenoloss.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * @author ybd
 * @date 2020/4/13
 * @contact yangbingdong1994@gmail.com
 */
@Configuration
@EnableConfigurationProperties(KafkaTopics.class)
@Order
public class KafkaTopicCreator implements InitializingBean {
    private final KafkaTopics kafkaTopics;
    private final GenericWebApplicationContext context;

    public KafkaTopicCreator(KafkaTopics kafkaTopics, GenericWebApplicationContext genericContext) {
        this.kafkaTopics = kafkaTopics;
        this.context = genericContext;
    }

    @Override
    public void afterPropertiesSet() {
        kafkaTopics.getTopics().forEach(t -> context.registerBean(t.name, NewTopic.class, t::toNewTopic));
    }
}
