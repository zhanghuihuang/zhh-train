package com.zhh.train.algorithm.sort;

import java.util.Arrays;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     希尔排序:插入排序的改进版
 *     对数组进行分组,然后每组进行插入排序
 *     确定分组增量h,对半递减h至1
 * </pre>
 * @since : 2020/6/3 11:41 上午
 */
public class ShellSort {
    public static void main(String[] args) {
        Integer[] unsorted = {8, 2, 4, 5, 1, 9, 6, 7, 3, 0};
        sort(unsorted);
        System.out.println("最终选择排序结果:" + Arrays.toString(unsorted));
    }

    public static void sort(Comparable[] unsorted) {
        //确定分组增量
        int h = 1;
        int len = unsorted.length / 2;
        while (h < len) {
            h = 2 * h + 1;
        }
        //递减增量,分组进行插入排序
        while (h >= 1) {
            //找到待插入元素
            for (int i = h; i < unsorted.length; i++) {
                //把待插入元素插入到有序数组中
                for (int j = i; j >= h; j -= h) {
                    //把a[j]和a[j-h]对比
                    if (greater(unsorted[j - h], unsorted[j])) {
                        exchange(unsorted, j - h, j);
                    } else {
                        break;
                    }
                }
            }
            h = h / 2;
        }
    }

    public static boolean greater(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }

    private static void exchange(Comparable[] unsorted, int i, int j) {
        Comparable temp = unsorted[i];
        unsorted[i] = unsorted[j];
        unsorted[j] = temp;
    }
}
