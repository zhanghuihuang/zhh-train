package com.zhh.train.jvm;

/**
 * @author : page
 * @project : zhh-train
 * @description : 堆内存情况观察
 * @date : 2020/5/7 1:46 下午
 */
public class HeapDemo {
    private byte[] a = new byte[1024 * 100];

    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("线程核心数:" + runtime.availableProcessors());
        System.out.println("总内存-最小堆内存:" + runtime.totalMemory() / 1024 / 1024 + "M");
        System.out.println("最大堆内存:" + runtime.maxMemory() / 1024 / 1024 + "M");
        System.out.println("空闲内存:" + runtime.freeMemory() / 1024 / 1024 + "M");
        /*List<HeapDemo> heapDemos = new ArrayList<>();
        while (true) {
            heapDemos.add(new HeapDemo());
            Thread.sleep(5000);
        }*/
    }
}
