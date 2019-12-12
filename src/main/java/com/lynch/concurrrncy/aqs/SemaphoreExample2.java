package com.lynch.concurrrncy.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by lynch on 2019-12-12.
 * Semaphore的用法
 * 一次获取多个许可
 **/
@Slf4j
public class SemaphoreExample2 {
    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(20); // 信号量数目
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(3); //一次获取多个许可
                    test(threadNum);
                    semaphore.release(3);//一次释放多个许可
                }catch (Exception e){
                    log.error("exception",e);
                }
            });
        }
        executorService.shutdown();

    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}",threadNum);
        Thread.sleep(1000);

    }
}
