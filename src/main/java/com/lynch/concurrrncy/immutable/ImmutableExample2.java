package com.lynch.concurrrncy.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * Created by lynch on 2019-11-29. <br>
 * 不可修改对象
 * Collections.unmodifiableXXX
 **/
@Slf4j
public class ImmutableExample2 {
    private final static Integer a = 1;
    private final static String b = "2";

    private static Map<Integer, Integer> map =  Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {

        map.put(1, 3);//Exception in thread "main" java.lang.UnsupportedOperationException
        log.info("{}", map.get(1));

    }


}
