package com.yangbingdong.messagenoloss.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author ybd
 * @date 2020/4/13
 * @contact yangbingdong1994@gmail.com
 */
public class KafkaSerializer implements Serializer<Object> {

    @Override
    public byte[] serialize(String topic, Object data) {
        return JSONObject.toJSONBytes(data);
    }
}
