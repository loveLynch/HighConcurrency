package com.lynch.concurrrncy.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by lynch on 2019-12-12.
 * Semaphore的用法
 * 尝试获取许可
 **/
@Slf4j
public class SemaphoreExample4 {
    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(20); // 信号量数目
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    if(semaphore.tryAcquire(2000, TimeUnit.MILLISECONDS)) {//尝试获取一个许可,可以等待一毫秒的超时时间
                        test(threadNum);
                        semaphore.release();//释放一个许可
                    }
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
