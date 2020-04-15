package com.yangbingdong.messagenoloss.kafka.producer;

import com.alibaba.fastjson.JSONObject;
import com.yangbingdong.messagenoloss.core.MessageSupporter;
import com.yangbingdong.messagenoloss.core.MqPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ybd
 * @date 2020/4/13
 * @contact yangbingdong1994@gmail.com
 */
@Component
@Slf4j
public class KafkaPublisher implements MqPublisher {
    @Autowired
    private KafkaTemplate<?, Object> kafkaTemplate;

    @Override
    public void produce(MessageSupporter messageSupporter, String topic) {
        Map<String, Object> header = new HashMap<>(16);
        header.put(KafkaHeaders.TOPIC, topic);
        header.put(KafkaHeaders.TIMESTAMP, System.currentTimeMillis());
        header.put(KafkaHeaders.MESSAGE_KEY, messageSupporter.getId());
        GenericMessage<?> message = new GenericMessage<>(JSONObject.toJSONString(messageSupporter.getPayload()), header);
        kafkaTemplate.send(message);
    }
}
