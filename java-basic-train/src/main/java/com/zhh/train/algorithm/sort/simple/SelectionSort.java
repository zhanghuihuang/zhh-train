package com.zhh.train.algorithm.sort.simple;

import com.zhh.train.algorithm.sort.AbstractSort;

import java.util.Arrays;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     选择排序:默认选未排序数组第一个为最小,然后从未排序第二个元素起,跟最小值比较,如果更小,则更新最小索引
 *     每一轮选择出最小元素索引,然后跟未排序的第一个元素交换,进行n-1轮
 * </pre>
 * @since : 2020/6/12 1:53 下午
 */
public class SelectionSort extends AbstractSort {
    public static void sort(Comparable[] unsorted) {
        sort(unsorted, false);
    }

    public static void sort(Comparable[] unsorted, boolean printFlow) {
        for (int i = 0; i < unsorted.length - 1; i++) {
            int minIndex = i;
            if (printFlow && unsorted.length <= 10) {
                System.out.println("\t初始最小索引/值:" + minIndex + "/" + unsorted[minIndex]);
            }
            for (int j = i + 1; j < unsorted.length; j++) {
                if (greater(unsorted[minIndex], unsorted[j])) {
                    minIndex = j;
                }
                if (printFlow && unsorted.length <= 10) {
                    System.out.println("\t最小索引/值:" + minIndex + "/" + unsorted[minIndex]);
                }
            }
            if (minIndex != i) {
                exchange(unsorted, minIndex, i);
            }
            if (printFlow) {
                System.out.println("第" + (i + 1) + "次选择:" + Arrays.toString(unsorted));
            }
        }
    }
}
