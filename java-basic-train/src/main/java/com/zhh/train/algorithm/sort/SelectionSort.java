package com.zhh.train.algorithm.sort;

import java.util.Arrays;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     选择排序:每一轮假设未排序部分的第一个元素为最小索引,一次往后遍历,找出最小索引,然后跟未排序第一个元素交换
 *     选择最小值往前放
 * </pre>
 * @since : 2020/6/11 10:31 上午
 */
public class SelectionSort extends AbstractSort {
    public static void sort(Comparable[] unsorted) {
        sort(unsorted, false);
    }

    public static void sort(Comparable[] unsorted, boolean printFlow) {
        boolean printDetail = unsorted.length <= 10;
        //确定选择最小元素的轮数n-1轮
        for (int i = 0; i < unsorted.length - 1; i++) {
            int minIndex = i;
            //每轮默认以i为最小元素索引,从下一个开始比较
            for (int j = i + 1; j < unsorted.length; j++) {
                if (greater(unsorted[minIndex], unsorted[j])) {
                    minIndex = j;
                }
                if (printFlow && printDetail) {
                    System.out.println("\t目前最小索引:" + minIndex);
                }
            }
            if (i != minIndex) {
                exchange(unsorted, minIndex, i);
            }
            if (printFlow) {
                System.out.println("第" + (i + 1) + "次选择:" + Arrays.toString(unsorted));
            }
        }
    }
}
