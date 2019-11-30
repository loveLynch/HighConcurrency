package com.lynch.concurrrncy.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by lynch on 2019-11-29. <br>
 * 不可修改对象
 * ImmutableXXX
 * 线程安全
 **/
@Slf4j
public class ImmutableExample3 {
    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);
    private final static ImmutableSet set = ImmutableSet.copyOf(list);
    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);
    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder()
            .put(1, 2).put(3, 4).put(5, 6).build();

    public static void main(String[] args) {
//        list.add(4);//Exception in thread "main" java.lang.UnsupportedOperationException
//        set.add(4);//Exception in thread "main" java.lang.UnsupportedOperationException
//        map.put(1, 3);//Exception in thread "main" java.lang.UnsupportedOperationException
//        map2.put(1, 3);//Exception in thread "main" java.lang.UnsupportedOperationException
    }


}
