package com.zhh.train.algorithm.sort;

import java.util.Random;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 * </pre>
 * @since : 2020/6/3 11:24 上午
 */
public class SortTest {
    public static void main(String[] args) {
        Random random = new Random();
        Integer[] nums = new Integer[100000];
        for (int i = 0; i < 100000; i++) {
            nums[i] = random.nextInt(100000);
        }
        long start = System.currentTimeMillis();
        //BubbleSort.sort(nums);
        InsertionSort.sort(nums);
        //QuickSort.sort(nums);
        long end = System.currentTimeMillis();
        System.out.println("排序时间:" + (end - start) + "ms");
    }
}
