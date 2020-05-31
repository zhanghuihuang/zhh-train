package com.zhh.train.concurrent.stream;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/5/8 10:04 上午
 */
public class ConcurrentStreamDemo {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) {
            nums[i] = i;
        }

        Arrays.stream(nums).parallel().forEach(i -> {
            atomicInteger.getAndIncrement();
        });
        System.out.println(atomicInteger);
    }
}
