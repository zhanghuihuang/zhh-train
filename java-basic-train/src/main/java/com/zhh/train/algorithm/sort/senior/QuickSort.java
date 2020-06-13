package com.zhh.train.algorithm.sort.senior;

import com.zhh.train.algorithm.sort.AbstractSort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     快速排序:也是分治法,选取一个基准数,遍历其他数,比基准数小的放左边,比基准数大的放右边
 *     即对数组按基准数进行分区
 *     然后对分区后的两个数组继续分区
 * </pre>
 * @since : 2020/6/13 12:48 下午
 */
public class QuickSort extends AbstractSort {
    public static void sort(Comparable[] unsorted) {
        sort(unsorted, 0, unsorted.length - 1);
    }

    public static void sort(Comparable[] unsorted, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int partition = partition(unsorted, lo, hi);
        sort(unsorted, lo, partition - 1);
        sort(unsorted, partition + 1, hi);
    }

    /**
     * 对lo到hi索引位置的元素进行分区
     * 以lo位置元素为基准值,比lo元素小的元素,放这个元素左边,反之放这个元素的右边
     * 定义两个指针left,right,分别从左右两边移动,先从右找到一个比lo元素小的,再从左找到一个比lo元素大的,liangge交换
     *
     * @param unsorted
     * @param lo
     * @param hi
     * @return
     */
    public static int partition(Comparable[] unsorted, int lo, int hi) {
        //选取lo处的元素为分界值
        Comparable boundary = unsorted[lo];
        //定义两个指针
        int left = lo, right = hi + 1;
        while (true) {
            while (less(boundary, unsorted[--right])) {
                if (right == left) {
                    break;
                }
            }
            while (less(unsorted[++left], boundary)) {
                if (right == left) {
                    break;
                }
            }
            if (left >= right) {
                break;
            } else {
                exchange(unsorted, left, right);
            }
        }
        //交换分界值和右指针指向的值
        exchange(unsorted, lo, right);
        return right;
    }
}
