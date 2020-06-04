package com.zhh.train.algorithm.sort;

import java.util.Arrays;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     插入排序:每轮拿未排序的第一个元素和已排序元素倒序遍历比较,小的就往前插入,直到遇到比它大的停止
 *     初始时,以第一个元素作为已排序元素
 * </pre>
 * @since : 2020/6/2 4:31 下午
 */
public class InsertionSort extends AbstractSort {
    public static void main(String[] args) {
        System.out.println("最终选择排序结果:" + Arrays.toString(sort(new Integer[]{8, 2, 4, 5, 1, 9, 6, 7, 3, 0})));
    }

    public static Comparable[] sort(Comparable[] unsorted) {
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.unsorted = unsorted;
        //进行n-1轮选择
        //System.out.println("初始化顺序:" + Arrays.toString(insertionSort.unsorted));
        for (int i = 1; i <= insertionSort.unsorted.length - 1; i++) {
            for (int j = i; j > 0; j--) {
                if (insertionSort.greater(j - 1, j)) {
                    insertionSort.exchange(j - 1, j);
                } else {
                    break;
                }
            }
            //System.out.println("第" + i + "次插入:" + Arrays.toString(insertionSort.unsorted));
        }
        return insertionSort.unsorted;
    }
}
