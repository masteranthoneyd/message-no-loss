package com.yangbingdong.messagenoloss.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ybd
 * @date 2020/4/23
 * @contact yangbingdong1994@gmail.com
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RabbitListener
public @interface MessagingListener {
    @AliasFor(annotation = RabbitListener.class, attribute = "queues")
    String topic();
}
