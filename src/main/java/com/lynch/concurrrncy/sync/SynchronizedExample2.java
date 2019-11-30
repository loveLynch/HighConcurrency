package com.lynch.concurrrncy.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lynch on 2019-11-28. <br>
 **/
@Slf4j
public class SynchronizedExample2 {

    //修饰一个类
    public void test1(int j) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    //修饰一个静态方法
    public static synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //作用于类的所有对象
        executorService.execute(() -> {
            example1.test1(1);
        });
        executorService.execute(() -> {
            example2.test1(2);
        });
        //作用于类的所有对象
//        executorService.execute(() -> {
//            example1.test2(1);
//        });
//        executorService.execute(() -> {
//            example2.test2(2);
//        });
        executorService.shutdown();
    }
}

