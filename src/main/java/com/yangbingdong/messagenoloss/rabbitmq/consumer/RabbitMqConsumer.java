package com.yangbingdong.messagenoloss.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.yangbingdong.messagenoloss.domain.TestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ybd
 * @date 2020/4/11
 * @contact yangbingdong1994@gmail.com
 */
@Component
@Slf4j
public class RabbitMqConsumer implements InitializingBean {

    @Value("${client-id}")
    private String clientId;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "test-topic",durable = "true"),
                    exchange = @Exchange(name = "test-topic"),
                    key="test-topic"
            )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload TestMessage testMessage, @Headers Map<String,Object> headers, Channel channel) throws Exception {
        try {
            log.info("收到消息, 当前线程: {}, 消息内容: {}", Thread.currentThread().getId(), testMessage);
        } catch (Exception e) {
            // 更新为消费失败
            log.error("消费异常");
        } finally {
            // multiple 为 true 代表批量确认
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG),false);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("6666666666666 " + clientId);
    }
}
