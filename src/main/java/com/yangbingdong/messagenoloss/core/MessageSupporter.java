package com.yangbingdong.messagenoloss.core;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ybd
 * @date 2020/4/13
 * @contact yangbingdong1994@gmail.com
 */
@Data
@AllArgsConstructor
public class MessageSupporter {

    private String id;

    private Object payload;

    private long delay;

    public static MessageSupporter of(String id, Object payload) {
        return new MessageSupporter(id, payload, 0L);
    }

}
