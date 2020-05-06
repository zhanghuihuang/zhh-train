package com.zhh.train.concurrent.cas;

import java.util.concurrent.TimeUnit;

/**
 * @author : page
 * @project : zhh-train
 * @description : Volatile
 * 1.保证可见性
 * 2.不保证原子性
 * 3.禁止指令重排序
 * @date : 2020/5/4 7:56 下午
 */
class Resource1 {
    public boolean flag = true;
}

public class VolatileDemo {
    public static volatile boolean flag = true;

    public static void main(String[] args) {
        Resource1 resource1 = new Resource1();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = false;
        }).start();
        while (flag) {

        }
    }
}
