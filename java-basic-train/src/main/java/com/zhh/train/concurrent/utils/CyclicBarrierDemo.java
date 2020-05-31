package com.zhh.train.concurrent.utils;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 等到所有线程都达到某个节点时,可以继续下一个循环
 * @date : 2020/5/15 4:23 下午
 */
public class CyclicBarrierDemo {
    static class TaskThread extends Thread {

        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " 到达栅栏 A");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 A");

                Thread.sleep(2000);
                System.out.println(getName() + " 到达栅栏 B");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //demo1();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println(Thread.currentThread().getName() + "完成本次循环");
        });
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            FutureTask<Integer> task = new FutureTask<>(() -> {
                System.out.println(Thread.currentThread().getName() + "到达A" + finalI);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "冲破A" + finalI);
                System.out.println(Thread.currentThread().getName() + "到达B" + finalI);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "冲破B" + finalI);
                return finalI;
            });
            threadPool.submit(task);
        }
        threadPool.shutdown();
    }

    private static void demo1() {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
            }
        });

        for (int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }
}
