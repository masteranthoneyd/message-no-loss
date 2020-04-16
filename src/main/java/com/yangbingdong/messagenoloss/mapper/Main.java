package com.yangbingdong.messagenoloss.mapper;

import static com.yangbingdong.messagenoloss.mapper.SourceTargetMapper.SOURCE_TARGET_MAPPER;

/**
 * @author ybd
 * @date 2020/4/16
 * @contact yangbingdong1994@gmail.com
 */
public class Main {

    public static void main(String[] args) {
        toTarget();
        copy();
    }

    private static void toTarget() {
        Source source = new Source();
        source.setId(666L);
        source.setTest("888");

        Target target = SOURCE_TARGET_MAPPER.toTarget(source);
        System.out.println(target);
    }

    public static void copy() {
        Source source = new Source();
        source.setId(666L);
        // source.setTest("999");

        Target target = new Target();
        target.setId(777L);
        target.setTesting(888L);

        SOURCE_TARGET_MAPPER.update(source, target);
        System.out.println(target);
    }
}
