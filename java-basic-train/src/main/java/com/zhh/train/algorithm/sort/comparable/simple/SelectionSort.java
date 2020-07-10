package com.zhh.train.algorithm.sort.comparable.simple;

import com.zhh.train.algorithm.sort.AbstractSort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     选择排序:
 *     每轮选择一个最小元素的索引,然后跟未排序的第一个元素交换
 * </pre>
 * @since : 2020/6/12 1:53 下午
 */
public class SelectionSort extends AbstractSort {
    public static void sort(Comparable[] unsorted) {
        sort(unsorted, false);
    }

    public static void sort(Comparable[] unsorted, boolean printFlow) {
        for (int i = 0; i < unsorted.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < unsorted.length; j++) {
                if (greater(unsorted[minIndex], unsorted[j])) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                exchange(unsorted, minIndex, i);
            }
        }
    }
}
