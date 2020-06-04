package com.zhh.train.algorithm.sort;

import java.util.Arrays;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     选择排序:每一轮找出最小值对应的索引,跟未排序数组第一个元素交换,进行n-1轮的最小值选择
 * </pre>
 * @since : 2020/6/2 4:04 下午
 */
public class SelectionSort extends AbstractSort {

    public static void main(String[] args) {
        System.out.println("最终选择排序结果:" + Arrays.toString(sort(new Integer[]{8, 2, 4, 5, 1, 9, 6, 7, 3, 0})));
    }

    public static Comparable[] sort(Comparable[] unsorted) {
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.unsorted = unsorted;
        //进行n-1轮选择
        System.out.println("初始化顺序:" + Arrays.toString(selectionSort.unsorted));
        for (int i = 0; i < selectionSort.unsorted.length - 1; i++) {
            //每次假设以未排序数组的第一个索引为最小值
            int minIndex = i;
            for (int j = i + 1; j < selectionSort.unsorted.length; j++) {
                if (selectionSort.greater(minIndex, j)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                selectionSort.exchange(minIndex, i);
            }
            System.out.println("第" + (i + 1) + "次选择:" + Arrays.toString(selectionSort.unsorted));
        }
        return selectionSort.unsorted;
    }
}
