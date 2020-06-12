package com.zhh.train.algorithm.sort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     希尔排序:插入排序的改良版,时间复杂度为NlogN,缩小增量排序
 *     确定一个增量,将数组按增量进行插入排序
 *     每次缩小分组增量,直到增量为1时,才对整个数组进行插入排序
 * </pre>
 * @since : 2020/6/11 11:08 上午
 */
public class ShellSort extends AbstractSort {

    public static void sort(Comparable[] unsorted) {
        //确定初始分组增量
        int step = unsorted.length / 2;
        //对分组增量确定的数组进行插入排序,并减少分组增量
        while (step >= 1) {
            //确定待插入元素
            for (int i = step; i < unsorted.length; i++) {
                for (int j = i; j >= step; j -= step) {
                    if (greater(unsorted[j - step], unsorted[j])) {
                        exchange(unsorted, j - step, j);
                    } else {
                        break;
                    }
                }
            }
            step = step / 2;
        }
    }
}
