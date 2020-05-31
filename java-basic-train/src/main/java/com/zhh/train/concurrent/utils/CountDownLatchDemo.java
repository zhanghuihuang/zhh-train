package com.zhh.train.concurrent.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/5/15 4:05 下午
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            threadPool.execute(() -> {
                try {
                    System.out.println("线程完成:" + temp);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {

        }
        threadPool.shutdown();
        System.out.println("等上面10个线程执行完,再继续下一步工作");
    }
}
