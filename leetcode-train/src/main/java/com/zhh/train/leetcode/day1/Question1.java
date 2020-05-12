package com.zhh.train.leetcode.day1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 我们提供了一个类：
 * <p>
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 * <p>
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date : 2020/5/9 9:29 上午
 */
public class Question1 {

    private static volatile int flag = 1;

    private static int flag1 = 1;

    private static Lock lock = new ReentrantLock();
    private static Condition condition2 = lock.newCondition();
    private static Condition condition3 = lock.newCondition();

    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        //answer1();
        //answer2();
        //answer3();
        //answer4();
        //answer4();
        //answer5();
    }

    /**
     * 利用阻塞队列
     */
    public static void answer5() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
        Foo foo = new Foo();
        new Thread(() -> {
            while (blockingQueue.peek() == 1) {
                foo.one();
                blockingQueue.remove();
            }
        }, "线程1").start();
        new Thread(() -> {
            while (blockingQueue.peek() == 2) {
                foo.two();
                blockingQueue.remove();
            }
        }, "线程2").start();
        new Thread(() -> {
            while (blockingQueue.peek() != null && blockingQueue.peek() == 3) {
                foo.three();
                blockingQueue.remove();
            }
        }, "线程3").start();
    }

    /**
     * 利用lock+await+signal实现
     */
    public static void answer4() {
        Foo foo = new Foo();
        new Thread(() -> {
            lock.lock();
            try {
                foo.one();
                flag1 = 2;
            } finally {
                lock.unlock();
            }
        }, "线程1").start();
        new Thread(() -> {
            lock.lock();
            try {
                while (flag1 != 2) {
                    condition2.await();
                }
                foo.two();
                flag1 = 3;
                condition2.signalAll();
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }
        }, "线程2").start();
        new Thread(() -> {
            lock.lock();
            try {
                while (flag1 != 3) {
                    condition3.await();
                }
                foo.three();
                flag1 = 1;
                condition3.signalAll();
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }
        }, "线程3").start();
    }

    /**
     * 利用synchronized+wait+notifyAll进行线程间的通信
     */
    public static void answer3() {
        Foo foo = new Foo();
        new Thread(() -> {
            synchronized (foo) {
                foo.one();
                flag1 = 2;
                foo.notifyAll();
            }
        }, "线程1").start();
        new Thread(() -> {
            synchronized (foo) {
                while (flag1 != 2) {
                    try {
                        foo.wait();
                    } catch (InterruptedException e) {

                    }
                }
                foo.two();
                flag1 = 3;
                foo.notifyAll();
            }
        }, "线程2").start();
        new Thread(() -> {
            synchronized (foo) {
                while (flag1 != 3) {
                    try {
                        foo.wait();
                    } catch (InterruptedException e) {

                    }
                }
                foo.three();
                flag1 = 1;
                foo.notifyAll();
            }
        }, "线程3").start();
    }

    /**
     * 利用volatile保证变量线程间的可见性
     */
    public static void answer2() {
        Foo foo = new Foo();
        new Thread(() -> {
            foo.one();
            flag = 2;
        }, "线程1").start();
        new Thread(() -> {
            while (flag != 2) {

            }
            foo.two();
            flag = 3;
        }, "线程2").start();
        new Thread(() -> {
            while (flag != 3) {

            }
            foo.three();
        }, "线程3").start();
    }

    /**
     * 开启三个线程,利用join
     */
    public static void answer1() {
        Foo foo = new Foo();

        Thread thread1 = new Thread(() -> {
            foo.one();
        }, "线程1");
        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            foo.two();
        }, "线程2");
        Thread thread3 = new Thread(() -> {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            foo.three();
        }, "线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static class Foo {
        public void one() {
            System.out.print("one");
        }

        public void two() {
            System.out.print("two");
        }

        public void three() {
            System.out.print("three");
        }
    }
}
