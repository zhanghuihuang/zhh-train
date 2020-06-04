package com.zhh.train.jvm;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     查看jvm参数值:jinfo -flag 属性名 进程号
 *     修改jvm参数值:jinfo -flag 属性名=值 进程号  或  jinfo -flag [+|-]属性名 进程号
 *     -XX:+PrintGCDetails  boolean类型参数
 *     是否打印GC详情
 * </pre>
 * @since : 2020/5/31 7:08 下午
 */
public class XX01_PrintGCDetails {
    public static void main(String[] args) throws InterruptedException {
        RuntimeDemo.printJvmMemory();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            stringBuilder.append(i);
        }
        byte[] bytes = new byte[60 * 1024];
        System.out.println("===================");
        RuntimeDemo.printJvmMemory();
        stringBuilder = null;
        bytes = null;
        System.gc();
        System.out.println("手动gc一次");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
