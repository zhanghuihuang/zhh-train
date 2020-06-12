package com.zhh.train.algorithm.sort;

import java.util.Arrays;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     插入排序:每轮选取未排序的第一个元素,往已排序的元素从后往前插入,遇到比它小的元素,停止插入,进行n-1轮
 *     默认第一个元素已经排序好,从第二个元素开始算第一轮未排序的第一个元素
 * </pre>
 * @since : 2020/6/11 10:43 上午
 */
public class InsertionSort extends AbstractSort {
    public static void sort(Comparable[] unsorted) {
        sort(unsorted, false);
    }

    public static void sort(Comparable[] unsorted, boolean printFlow) {
        boolean printDetail = unsorted.length <= 10;
        for (int i = 1; i < unsorted.length; i++) {
            for (int j = i; j > 0; j--) {
                if (greater(unsorted[j - 1], unsorted[j])) {
                    //如果前一个元素比它大,则进行元素交换
                    exchange(unsorted, j - 1, j);
                } else {
                    break;
                }
                if (printFlow && printDetail) {
                    System.out.println("\t" + Arrays.toString(unsorted));
                }
            }
            if (printFlow) {
                System.out.println("第" + i + "次插入:" + Arrays.toString(unsorted));
            }
        }
    }
}
