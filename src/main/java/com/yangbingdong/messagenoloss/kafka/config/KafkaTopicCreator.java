package com.yangbingdong.messagenoloss.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * @author ybd
 * @date 2020/4/13
 * @contact yangbingdong1994@gmail.com
 */
@Configuration
@Order
public class KafkaTopicCreator implements InitializingBean {
    private final KafkaTopics configurations;
    private final GenericWebApplicationContext context;

    public KafkaTopicCreator(KafkaTopics configurations, GenericWebApplicationContext genericContext) {
        this.configurations = configurations;
        this.context = genericContext;
    }

    @Override
    public void afterPropertiesSet() {
        configurations.getTopics().forEach(t -> context.registerBean(t.name, NewTopic.class, t::toNewTopic));
    }
}
