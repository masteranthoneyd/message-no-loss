package com.yangbingdong.messagenoloss.rabbitmq.consumer;

import com.yangbingdong.messagenoloss.rabbitmq.config.MessagingMessageListenerAdapterDecorator;
import com.yangbingdong.messagenoloss.config.MessagingListener;
import com.yangbingdong.messagenoloss.domain.TestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author ybd
 * @date 2020/4/11
 * @contact yangbingdong1994@gmail.com
 */
@Component
@Slf4j
public class RabbitMqConsumer {

    @SuppressWarnings("ConstantConditions")
    @MessagingListener(topic = "test-topic")
    public void onOrderMessage(Message<TestMessage> message) throws Exception {
        try {
            log.info("收到消息, 当前线程: {}, 消息内容: {}", Thread.currentThread().getId(), message.getPayload());
        } catch (Exception e) {
            // 更新为消费失败
            log.error("消费异常");
        } finally {
            // multiple 为 true 代表批量确认
            MessagingMessageListenerAdapterDecorator.THREAD_LOCAL
                    .get()
                    .basicAck((Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG),false);
        }
    }


}
