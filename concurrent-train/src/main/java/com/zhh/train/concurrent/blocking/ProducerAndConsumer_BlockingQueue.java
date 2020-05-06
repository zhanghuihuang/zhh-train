package com.zhh.train.concurrent.blocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/5/5 6:56 下午
 */
class CakeShop3 {
    private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    public void add() {
        try {
            queue.put(1);
            System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        try {
            queue.take();
            System.out.println("【消费者" + Thread.currentThread().getName() + "】消费了一个产品，现库存" + queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class ProducerAndConsumer_BlockingQueue {
    public static void main(String[] args) {
        CakeShop3 cakeShop3 = new CakeShop3();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                cakeShop3.add();
            }
        }, "线程1").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                cakeShop3.remove();
            }
        }, "线程2").start();
    }
}
