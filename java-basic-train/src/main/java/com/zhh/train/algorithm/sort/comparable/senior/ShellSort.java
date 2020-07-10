package com.zhh.train.algorithm.sort.comparable.senior;

import com.zhh.train.algorithm.sort.AbstractSort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     希尔排序:优化版的插入排序,也叫缩小增量排序
 *     确定一个增量,正常取待排序数组长度的二分之一
 *     以这个增量做一次插入排序,然后缩小增量,继续做一次插入排序,知道增量小于1
 * </pre>
 * @since : 2020/6/13 8:08 上午
 */
public class ShellSort extends AbstractSort {
    public static void sort(Comparable[] unsorted, boolean printFlow) {
        if (unsorted.length <= 1) {
            return;
        }
        //确定初始增量
        int step = unsorted.length / 2;
        //循环缩减增量进行插入排序,直到增量小于1
        while (step >= 1) {
            //根据增量步长,做一次增量插入排序,从增量开始后的元素都是待插入元素
            for (int i = step; i < unsorted.length; i++) {
                //待插入元素,跟他前面跨步长的元素进行比较插入
                for (int j = i - step; j >= 0; j -= step) {
                    if (greater(unsorted[j], unsorted[j + step])) {
                        exchange(unsorted, j, j + step);
                    }
                }
            }
            //缩减增量值
            step = step / 2;
        }
    }
}
