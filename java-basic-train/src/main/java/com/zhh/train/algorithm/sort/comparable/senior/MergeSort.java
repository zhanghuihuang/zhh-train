package com.zhh.train.algorithm.sort.comparable.senior;

import com.zhh.train.algorithm.sort.AbstractSort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     归并排序:
 *     对待排序数组分成两个长度差不多的数组,递归拆分,直到最小单元不可分,然后把拆分的数组合并时,按顺序存放
 *     需要借助一个辅助数组,来存放合并时的数组
 * </pre>
 * @since : 2020/6/13 11:02 上午
 */
public class MergeSort extends AbstractSort {
    private static Comparable[] tempArr;

    public static void sort(Comparable[] unsorted) {
        if (unsorted.length <= 1) {
            return;
        }
        //初始化辅助数组
        tempArr = new Comparable[unsorted.length];
        sort(unsorted, 0, unsorted.length - 1);
    }

    /**
     * 对数组unsorted的lo到hi索引元素进行排序
     *
     * @param unsorted
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] unsorted, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        //将数组拆分成两个数组进行排序
        int mid = lo + (hi - lo) / 2;
        sort(unsorted, lo, mid);
        sort(unsorted, mid + 1, hi);
        //对拆分的数组进行归并排序
        merge(unsorted, lo, mid, hi);
    }

    /**
     * 对数组unsorted的lo到hi索引元素进行归并排序
     *
     * @param unsorted
     * @param lo
     * @param hi
     */
    private static void merge(Comparable[] unsorted, int lo, int mid, int hi) {
        //借助辅助数组,定义两个指针分别遍历lo-mid和mid+1-hi两个数组,按从小到大放入辅助数组
        //定义一个临时指针,用来控制元素放入辅助数组的位置
        int p1 = lo, p2 = mid + 1, temp = lo;
        //比较p1和p2两个指针所指元素,比较小的元素放入辅助数组且指针移动1位
        while (p1 <= mid && p2 <= hi) {
            if (less(unsorted[p1], unsorted[p2])) {
                tempArr[temp++] = unsorted[p1++];
            } else {
                tempArr[temp++] = unsorted[p2++];
            }
        }
        while (p1 <= mid) {
            //剩下的元素依次放入辅助数组
            tempArr[temp++] = unsorted[p1++];
        }
        while (p2 <= hi) {
            //剩下的元素依次放入辅助数组
            tempArr[temp++] = unsorted[p2++];
        }
        //复制排序好的数组
        System.arraycopy(tempArr, lo, unsorted, lo, hi - lo + 1);
    }
}
