package com.yangbingdong.messagenoloss.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author ybd
 * @date 2020/4/13
 * @contact yangbingdong1994@gmail.com
 */
public class KafkaDeserializer implements Deserializer<Object> {

    @Override
    public Object deserialize(String topic, byte[] data) {
        return JSONObject.parse(data);
    }
}
