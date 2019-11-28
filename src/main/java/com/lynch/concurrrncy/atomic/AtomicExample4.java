package com.lynch.concurrrncy.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by lynch on 2019-11-28. <br>
 **/
@Slf4j
//线程安全
// AtomicReference

public class AtomicExample4 {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2);//2
        count.compareAndSet(0, 1);//no
        count.compareAndSet(1, 3);//no
        count.compareAndSet(2, 4);//4
        count.compareAndSet(3, 5);//no

        log.info("concurrrncy:{}", count.get());

    }


}
