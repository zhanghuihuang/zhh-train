package com.zhh.train.algorithm.sort.comparable.simple;

import com.zhh.train.algorithm.sort.AbstractSort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     冒泡排序:
 *     每一轮从第一个元素开始,两两比较,前一个元素大于后一个元素,则交换位置
 *     每轮参加冒泡的元素都会少一个
 * </pre>
 * @since : 2020/6/12 1:53 下午
 */
public class BubbleSort extends AbstractSort {
    public static void sort(Comparable[] unsorted) {
        sort(unsorted, false);
    }

    public static void sort(Comparable[] unsorted, boolean printFlow) {
        if (unsorted.length <= 1) {
            return;
        }
        for (int i = 0; i < unsorted.length; i++) {
            for (int j = 0; j < unsorted.length - i - 1; j++) {
                if (greater(unsorted[j], unsorted[j + 1])) {
                    exchange(unsorted, j, j + 1);
                }
            }
        }
    }
}
