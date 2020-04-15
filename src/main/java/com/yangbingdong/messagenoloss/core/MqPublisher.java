package com.yangbingdong.messagenoloss.core;

/**
 * @author ybd
 * @date 2020/4/13
 * @contact yangbingdong1994@gmail.com
 */
public interface MqPublisher {
    void produce(MessageSupporter messageSupporter, String topic);
}
