package com.yangbingdong.messagenoloss.api;

import com.yangbingdong.messagenoloss.core.MessageSupporter;
import com.yangbingdong.messagenoloss.domain.TestMessage;
import com.yangbingdong.messagenoloss.kafka.producer.KafkaPublisher;
import com.yangbingdong.messagenoloss.rabbitmq.producer.RabbitMqPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ybd
 * @date 2020/4/13
 * @contact yangbingdong1994@gmail.com
 */
@RestController
public class MessageApi {

    @Autowired
    private RabbitMqPublisher rabbitMqProducer;
    @Autowired
    private KafkaPublisher kafkaPublisher;

    @GetMapping("/rabbitmq/{message}")
    public String publishRabbitMqMessage(@PathVariable String message) {
        TestMessage testMessage = new TestMessage(666L, message);
        MessageSupporter supporter = MessageSupporter.of(testMessage.getId().toString(), testMessage);
        rabbitMqProducer.produce(supporter, "test-topic");
        return "OK";
    }

    @GetMapping("/kafka/{message}")
    public String publishKafkaMqMessage(@PathVariable String message) {
        TestMessage testMessage = new TestMessage(666L, message);
        MessageSupporter supporter = MessageSupporter.of(testMessage.getId().toString(), testMessage);
        kafkaPublisher.produce(supporter, "test-topic-error");
        return "OK";
    }

    @GetMapping("/kafka/2/{message}")
    public String publishKafkaMqMessage2(@PathVariable String message) {
        TestMessage testMessage = new TestMessage(666L, message);
        MessageSupporter supporter = MessageSupporter.of(testMessage.getId().toString(), testMessage);
        kafkaPublisher.produce(supporter, "test-topic-2");
        return "OK";
    }
}
