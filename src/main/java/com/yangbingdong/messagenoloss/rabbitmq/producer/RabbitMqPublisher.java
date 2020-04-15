package com.yangbingdong.messagenoloss.rabbitmq.producer;

import com.yangbingdong.messagenoloss.core.MessageSupporter;
import com.yangbingdong.messagenoloss.core.MqPublisher;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ybd
 * @date 2020/4/11
 * @contact yangbingdong1994@gmail.com
 */
@Component
public class RabbitMqPublisher implements MqPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void produce(MessageSupporter messageSupporter, String topic) {
        rabbitTemplate.convertAndSend(topic, topic, messageSupporter.getPayload(), new CorrelationData(messageSupporter.getId()));
    }
}
