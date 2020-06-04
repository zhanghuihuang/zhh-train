package com.zhh.train.jvm;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     jvm 运行时数据区域
 *     独占区: java虚拟机栈,本地方法栈,程序计数器
 *     共享区: 堆,方法区(jdk8开始叫元空间,利用直接内存,以前叫永久代)
 *     堆:新生代区和老年代区,默认最大堆内存为机器物理内存的1/4,最小堆内存为机器物理内存的1/64
 *     新生代和老年代比例: 1:2
 *          新生代分:Eden区,Survivor From区,Survivor To区,比例:8:1:1
 *     比如分配最大堆内存:-Xmx3G
 *          新生代:1G
 *              Eden区:0.8G
 *              From区:0.1G
 *              To区:0.1G
 *          老年代:2G
 * </pre>
 * @since : 2020/5/31 4:25 下午
 */
public class RuntimeDemo {
    public static void main(String[] args) {
        //配置堆内存参数: -Xms512m -Xmx1024m
        printJvmMemory();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printJvmMemory() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("核心数:" + runtime.availableProcessors());
        System.out.println("最大堆内存:" + runtime.maxMemory() / 1024 / 1024 + "M");
        System.out.println("最小堆内存:" + runtime.totalMemory() / 1024 / 1024 + "M");
        System.out.println("空闲内存:" + runtime.freeMemory() / 1024 / 1024 + "M");
    }
}
