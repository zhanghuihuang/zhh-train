package com.zhh.train.concurrent.lock;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * 死锁示例
 * 是什么?
 * 两个或两个以上的线程因相互争抢资源而造成的一种相互等待的现象
 * 如果没有外力干涉,线程将无法进行下去
 * 资源充足的情况,进程请求资源可以得到满足,造成死锁的可能性就很低
 * 否则就可能陷入死锁
 * <p>
 * 原因:
 * 系统资源不足
 * 进程运行推进的顺序不合适
 * 资源分配不当
 * <p>
 * 怎么办?
 * 1.尽量少在多个线程中使用多个资源
 * 2.排查死锁:jps查看进程号,jstack查看进程堆栈,找到死锁出现位置
 * @since : 2020/5/31 11:43 上午
 */
@Data
public class DeadLockDemo {
    private Object resourceA = new Object();
    private Object resourceB = new Object();

    /**
     * jps 查看进程pid
     * jstack pid 查看进程堆栈
     * Java stack information for the threads listed above:
     * ===================================================
     * "Thread-1":
     * at com.zhh.train.concurrent.lock.DeadLockDemo.lambda$main$1(DeadLockDemo.java:43)
     * - waiting to lock <0x000000076ae02918> (a java.lang.Object)
     * - locked <0x000000076ae02928> (a java.lang.Object)
     * at com.zhh.train.concurrent.lock.DeadLockDemo$$Lambda$2/1879492184.run(Unknown Source)
     * at java.lang.Thread.run(Thread.java:748)
     * "Thread-0":
     * at com.zhh.train.concurrent.lock.DeadLockDemo.lambda$main$0(DeadLockDemo.java:29)
     * - waiting to lock <0x000000076ae02928> (a java.lang.Object)
     * - locked <0x000000076ae02918> (a java.lang.Object)
     * at com.zhh.train.concurrent.lock.DeadLockDemo$$Lambda$1/1674896058.run(Unknown Source)
     * at java.lang.Thread.run(Thread.java:748)
     * <p>
     * Found 1 deadlock.
     *
     * @param args
     */
    public static void main(String[] args) {
        DeadLockDemo demo = new DeadLockDemo();
        new Thread(() -> {
            synchronized (demo.resourceA) {
                System.out.println("我持有资源A,现在要去抢夺资源B");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (demo.resourceB) {
                    System.out.println("我现在先持有资源A,又持有资源B");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (demo.resourceB) {
                System.out.println("我持有资源B,现在要去抢夺资源A");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (demo.resourceA) {
                    System.out.println("我现在先持有资源B,又持有资源A");
                }
            }
        }).start();
    }
}
