package com.zhh.train.concurrent.cas;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author : page
 * @project : zhh-train
 * @description : cas底层原理:利用cpu原语保证原子性
 * 存在的问题:
 * 1.ABA问题:原子引用+时间戳解决
 * 2.循环时间长开销大
 * 3.不能保证多个共享变量的原子操作
 * @date : 2020/5/6 5:30 下午
 */
@Data
class A {
    private volatile String name;
}

public class AtomicDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println(atomicInteger.compareAndSet(0, 1));
        System.out.println(atomicInteger.compareAndSet(0, 1));
        System.out.println(atomicInteger.compareAndSet(0, 1));
        System.out.println(atomicInteger.compareAndSet(1, 0));

        AtomicReference<A> atomicReference = new AtomicReference<>();
        A newValue = new A();
        newValue.setName("zhangsan");
        atomicReference.set(newValue);
        System.out.println(atomicReference.compareAndSet(newValue, new A()));
        System.out.println(atomicReference.compareAndSet(newValue, newValue));

        AtomicReferenceFieldUpdater<A, String> name = AtomicReferenceFieldUpdater.newUpdater(A.class, String.class, "name");
        System.out.println(name.compareAndSet(newValue, "zhangsan", "lisi"));
        System.out.println(name.compareAndSet(newValue, "lisi", "zhangsan"));
    }
}
