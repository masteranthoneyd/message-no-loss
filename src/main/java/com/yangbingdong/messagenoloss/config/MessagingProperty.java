package com.yangbingdong.messagenoloss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ybd
 * @date 2020/4/23
 * @contact yangbingdong1994@gmail.com
 */
@ConfigurationProperties(prefix = "messaging")
@Data
public class MessagingProperty {
    private MqType mqType;

    private List<Topic> topics = new ArrayList<>();

    @Data
    public static class Topic {
        private String name;

        private final Map<String, String> properties = new HashMap<>();
    }
}
