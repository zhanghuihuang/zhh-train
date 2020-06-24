package com.zhh.train.algorithm.sort;

import com.zhh.train.algorithm.sort.comparable.senior.QuickSort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 * </pre>
 * @since : 2020/6/11 9:58 上午
 */
public class SortTest {
    public static void main(String[] args) {
        Random random = new Random();
        int batch = 10;
        int seed = 1000;
        Integer[] nums = new Integer[batch];
        for (int i = 0; i < batch; i++) {
            nums[i] = random.nextInt(seed);
        }
        if (batch <= 100) {
            System.out.println("排序前:" + Arrays.toString(nums));
        }
        long start = System.currentTimeMillis();
        //BubbleSort.sort(nums, true);
        //SelectionSort.sort(nums, true);
        //InsertionSort.sort(nums, true);
        //ShellSort.sort(nums, true);
        //MergeSort.sort(nums);
        QuickSort.sort(nums);
        long end = System.currentTimeMillis();
        System.out.println("排序时间:" + (end - start) + "ms");
        if (batch <= 100) {
            System.out.println("排序后:" + Arrays.toString(nums));
        }
    }
}
