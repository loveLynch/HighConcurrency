package com.lynch.concurrrncy.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lynch on 2019-11-29. <br>
 * final
 * 不可修改对象
 * 线程不一定安全
 **/
@Slf4j
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";

    private final static Map<Integer, Integer> map = Maps.newHashMap();
//    private final static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        a=2;
//        b="3";
//        map = Maps.newHashMap();
        //线程不安全，引用对象不可修改，但值可被修改
        map.put(1, 3);
        log.info("{}", map.get(1));

    }

    private void test(final int a) {
//        a=1;
    }


}
