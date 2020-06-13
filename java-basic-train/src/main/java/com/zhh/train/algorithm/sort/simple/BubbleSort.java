package com.zhh.train.algorithm.sort.simple;

import com.zhh.train.algorithm.sort.AbstractSort;

import java.util.Arrays;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     冒泡排序:每一轮两两比较,大的往后冒泡,进行n-1轮,每轮少一个元素参与冒泡
 * </pre>
 * @since : 2020/6/12 1:53 下午
 */
public class BubbleSort extends AbstractSort {
    public static void sort(Comparable[] unsorted) {
        sort(unsorted, false);
    }

    public static void sort(Comparable[] unsorted, boolean printFlow) {
        for (int i = 0; i < unsorted.length - 1; i++) {
            for (int j = 0; j < unsorted.length - 1 - i; j++) {
                if (greater(unsorted[j], unsorted[j + 1])) {
                    exchange(unsorted, j, j + 1);
                }
                if (printFlow && unsorted.length <= 10) {
                    System.out.println("\t" + Arrays.toString(unsorted));
                }
            }
            if (printFlow) {
                System.out.println("第" + (i + 1) + "次冒泡:" + Arrays.toString(unsorted));
            }
        }
    }
}
