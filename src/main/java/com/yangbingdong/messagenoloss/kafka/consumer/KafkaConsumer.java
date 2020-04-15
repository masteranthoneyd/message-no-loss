package com.yangbingdong.messagenoloss.kafka.consumer;

import com.alibaba.fastjson.JSONObject;
import com.yangbingdong.messagenoloss.domain.TestMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author ybd
 * @date 2020/4/13
 * @contact yangbingdong1994@gmail.com
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic")
    public void consumeMessage(Acknowledgment acknowledgment, ConsumerRecord<String, String> consumerRecord) {
        try {
            TestMessage testMessage = JSONObject.parseObject(consumerRecord.value(), TestMessage.class);
            log.info("消费者消费1, 当前线程: {}, topic:{} partition:{} 的消息 -> {}, consumerRecord: {}", Thread.currentThread(), consumerRecord.topic(), consumerRecord.partition(), testMessage.toString(), consumerRecord);
        } catch (Exception e) {
            log.error("Kafka 消费异常", e);
        } finally {
            acknowledgment.acknowledge();
        }
    }

    @KafkaListener(topics = "test-topic-2")
    public void consumeMessage2(Acknowledgment acknowledgment, ConsumerRecord<String, String> consumerRecord) {
        try {
            TestMessage testMessage = JSONObject.parseObject(consumerRecord.value(), TestMessage.class);
            log.info("消费者2, 当前线程: {}, topic:{} partition:{} 的消息 -> {}, consumerRecord: {}", Thread.currentThread(), consumerRecord.topic(), consumerRecord.partition(), testMessage.toString(), consumerRecord);
        } catch (Exception e) {
            log.error("Kafka 消费异常", e);
        } finally {
            acknowledgment.acknowledge();
        }
    }
}
