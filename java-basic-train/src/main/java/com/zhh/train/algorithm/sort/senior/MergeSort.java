package com.zhh.train.algorithm.sort.senior;

import com.zhh.train.algorithm.sort.AbstractSort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     归并排序:二路归并
 *     将待排序数组一步一步分解至最小单元,然后再一步一步进行合并,并同时排序
 *     利用一个辅助数组来完成排序
 *     从中间对数组进行拆分,然后对左右两个数组递归调用排序方法
 * </pre>
 * @since : 2020/6/13 11:02 上午
 */
public class MergeSort extends AbstractSort {
    private static Comparable[] tempArr;

    public static void sort(Comparable[] unsorted) {
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
        //确保hi大于lo
        if (hi <= lo) {
            return;
        }
        //取lo到hi索引的中间位置,对数组进行分解两个
        int mid = lo + (hi - lo) / 2;
        //对拆分后的左右子组继续拆分排序
        sort(unsorted, lo, mid);
        sort(unsorted, mid + 1, hi);
        //对左右子组归并排序
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
        //对两个有序的子数组进行归并排序:lo-mid和mid+1到hi
        //定义p1和p2指针,分别遍历lo-mid和mid+1到hi,把最小的元素放到临时数组的相应索引位置
        int p1 = lo, p2 = mid + 1;
        int temp = lo;
        while (p1 <= mid && p2 <= hi) {
            if (less(unsorted[p1], unsorted[p2])) {
                tempArr[temp++] = unsorted[p1++];
            } else {
                tempArr[temp++] = unsorted[p2++];
            }
        }
        //遍历p1或p2剩下的元素,依次放入临时数组
        while (p1 <= mid) {
            tempArr[temp++] = unsorted[p1++];
        }
        while (p2 <= hi) {
            tempArr[temp++] = unsorted[p2++];
        }
        //复制临时数组到排序数组
        System.arraycopy(tempArr, lo, unsorted, lo, hi - lo + 1);
    }
}
