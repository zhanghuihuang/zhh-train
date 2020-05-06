package com.zhh.train.concurrent.blocking;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : page
 * @project : zhh-train
 * @description : 采用lock+await+signal实现
 * 面包店买面包,当柜台的蛋糕数少于10个时,需要通知厨师生产
 * 柜台上蛋糕数大于0时,可以进行购买
 * @date : 2020/5/5 6:56 下午
 */
class CakeShop2 {
    public int cakeNum = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            while (cakeNum >= 10) {
                //System.out.println("生产者,库存满了");
                condition.await();
            }
            cakeNum++;
            System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + cakeNum);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void remove() throws InterruptedException {
        lock.lock();
        try {
            while (cakeNum <= 0) {
                //System.out.println("消费者,没库存了");
                condition.await();
            }
            cakeNum--;
            System.out.println("【消费者" + Thread.currentThread().getName() + "】消费了一个产品，现库存" + cakeNum);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

public class ProducerAndConsumer_Lock {
    public static void main(String[] args) {
        CakeShop2 cakeShop2 = new CakeShop2();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    cakeShop2.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程1").start();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    cakeShop2.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程2").start();
    }
}