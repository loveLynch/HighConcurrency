package com.lynch.concurrrncy.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by lynch on 2019-11-28. <br>
 **/
@Slf4j
//线程安全
// 1。AtomicIntegerFieldUpdater

//不举例
// 2。AtomicStampedReference
//-----解决线程中的ABA问题
//-----通过添加版本号
// 3。AtomicLongArray

public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    @Getter
    public volatile int count = 100; //filename中volatile和非static字段


    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 1, {}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 2, {}", example5.getCount());
        } else {
            log.info("update failed. {} ", example5.getCount());


        }

    }


}
