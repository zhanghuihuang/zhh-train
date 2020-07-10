package com.zhh.train.algorithm.sort.comparable.simple;

import com.zhh.train.algorithm.sort.AbstractSort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     插入排序:
 *     每轮拿未排序元素的第一个往已排序元素从后往前遍历,如果比已排序元素小则交换位置,如果比已排序元素大,则停止
 * </pre>
 * @since : 2020/6/12 1:53 下午
 */
public class InsertionSort extends AbstractSort {
    public static void sort(Comparable[] unsorted) {
        sort(unsorted, false);
    }

    public static void sort(Comparable[] unsorted, boolean printFlow) {
        for (int i = 1; i < unsorted.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (greater(unsorted[j], unsorted[j + 1])) {
                    exchange(unsorted, j, j + 1);
                } else {
                    break;
                }
            }
        }
    }
}
