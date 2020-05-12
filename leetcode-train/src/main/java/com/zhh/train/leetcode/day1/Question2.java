package com.zhh.train.leetcode.day1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 多线程间顺序调用,实现A->B->C三个线程启动,需求如下
 * A打印5次,B打印10次,C打印15次
 * 接着
 * A打印5次,B打印10次,C打印15次
 * ...
 * 如果循环10次
 * <p>
 * 解题思路:
 * 利用lock+await+signal实现锁绑定多个条件,精确唤醒
 * @date : 2020/5/9 2:52 下午
 */
public class Question2 {

    public static void main(String[] args) {
        Foo foo = new Foo();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                foo.print5();
            }
        }, "线程1").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                foo.print10();
            }
        }, "线程2").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                foo.print15();
            }
        }, "线程3").start();
    }

    public static class Foo {
        int num = 1;//A:1,B:2,C:3
        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        public void print5() {
            lock.lock();
            try {
                //判断
                while (num != 1) {
                    c1.await();
                }
                //干活
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t " + i);
                }
                num = 2;
                //通知
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void print10() {
            lock.lock();
            try {
                //判断
                while (num != 2) {
                    c2.await();
                }
                //干活
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t " + i);
                }
                num = 3;
                //通知
                c3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void print15() {
            lock.lock();
            try {
                //判断
                while (num != 3) {
                    c3.await();
                }
                //干活
                for (int i = 1; i <= 15; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t " + i);
                }
                num = 1;
                //通知
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
