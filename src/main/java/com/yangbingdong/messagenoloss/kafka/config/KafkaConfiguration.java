package com.yangbingdong.messagenoloss.kafka.config;

import com.yangbingdong.messagenoloss.config.YamlPropertySourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author ybd
 * @date 2020/4/13
 * @contact yangbingdong1994@gmail.com
 */
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:MQ-CONF/kafka.yml")
@Configuration(proxyBeanMethods = false)
public class KafkaConfiguration {

    @Bean
    public ProducerListener<Object, Object> kafkaProducerListener() {
        return new KafkaProducerListener<>();
    }
}
