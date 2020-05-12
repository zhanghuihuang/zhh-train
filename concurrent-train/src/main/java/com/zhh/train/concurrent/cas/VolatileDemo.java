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
public class VolatileDemo {
    public static boolean flag = false;

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            System.out.println("waiting data...");
            while (!flag) {
            }
            System.out.println("success...");
        }, "线程1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            changeFlag();
        }, "线程2").start();
    }

    public static void changeFlag() {
        System.out.println("change flag start...");
        flag = true;
        System.out.println("change flag end...");
    }
}
