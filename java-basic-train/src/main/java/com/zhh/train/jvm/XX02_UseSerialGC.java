package com.zhh.train.jvm;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     查看jvm参数值:jinfo -flag 属性名 进程号
 *     修改jvm参数值:jinfo -flag 属性名=值 进程号  或  jinfo -flag [+|-]属性名 进程号
 *     -XX:+UseSerialGC  boolean类型参数
 *     是否启用串行垃圾回收器
 * </pre>
 * @since : 2020/5/31 7:08 下午
 */
public class XX02_UseSerialGC {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        o = null;
        System.gc();
        System.out.println("手动gc一次");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
