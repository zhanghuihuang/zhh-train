package com.zhh.train.concurrent.blocking;

/**
 * @author : page
 * @project : zhh-train
 * @description : 采用synchronized+wait+notify实现
 * 面包店买面包,当柜台的蛋糕数少于10个时,需要通知厨师生产
 * 柜台上蛋糕数大于0时,可以进行购买
 * @date : 2020/5/5 6:56 下午
 */
class CakeShop1 {
    public volatile int cakeNum = 0;

    public synchronized void add() throws InterruptedException {
        while (cakeNum >= 10) {
            System.out.println("生产者,库存满了");
            this.wait();
        }
        cakeNum++;
        System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + cakeNum);
        this.notifyAll();
    }

    public synchronized void remove() throws InterruptedException {
        while (cakeNum <= 0) {
            System.out.println("消费者,没库存了");
            this.wait();
        }
        cakeNum--;
        System.out.println("【消费者" + Thread.currentThread().getName() + "】消费了一个产品，现库存" + cakeNum);
        this.notifyAll();
    }
}

public class ProducerAndConsumer_Sync {
    public static void main(String[] args) {
        CakeShop1 cakeShop1 = new CakeShop1();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    cakeShop1.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程1").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    cakeShop1.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程2").start();
    }
}
