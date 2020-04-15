package com.yangbingdong.messagenoloss;

import com.yangbingdong.messagenoloss.core.MessageSupporter;
import com.yangbingdong.messagenoloss.domain.TestMessage;
import com.yangbingdong.messagenoloss.rabbitmq.producer.RabbitMqPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ybd
 * @date 2020/4/11
 * @contact yangbingdong1994@gmail.com
 */
@SpringBootTest
// @RunWith(SpringRunner.class)
public class RabbitMqPublisherTester {

    @Autowired
    private RabbitMqPublisher rabbitMqProducer;

    @Test
    public void send() {
        TestMessage testMessage = new TestMessage(666L, "TestMessage");
        MessageSupporter supporter = MessageSupporter.of(testMessage.getId().toString(), testMessage);
        rabbitMqProducer.produce(supporter, "test-topic");
    }

}
