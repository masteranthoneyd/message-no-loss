package com.yangbingdong.messagenoloss.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author ybd
 * @date 2020/4/13
 * @contact yangbingdong1994@gmail.com
 */
@Slf4j
public class KafkaProducerListener<K, V> implements ProducerListener<K, V> {

    @Override
    public void onSuccess(String topic, Integer partition, K key, V value, RecordMetadata recordMetadata) {
        log.info("Kafka produce success, topic: {}, partition: {}, K: {}, V: {}, recordMetadata: {}", topic, partition, key, value, recordMetadata);
    }

    @Override
    public void onError(String topic, Integer partition, K key, V value, Exception exception) {
        log.error("Kafka produce fail, topic: " + topic + ", partition: " + partition + ", K: " + key + ", V: " + value, exception);
    }
}
