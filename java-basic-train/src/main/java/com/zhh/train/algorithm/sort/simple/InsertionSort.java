package com.zhh.train.algorithm.sort.simple;

import com.zhh.train.algorithm.sort.AbstractSort;

import java.util.Arrays;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     插入排序:假定第一个元素是已排序元素,从第二个元素开始,从后往前遍历已排序元素
 *     比已排序元素小就往前插入,直到遇到比它大的元素停止插入
 * </pre>
 * @since : 2020/6/12 1:53 下午
 */
public class InsertionSort extends AbstractSort {
    public static void sort(Comparable[] unsorted) {
        sort(unsorted, false);
    }

    public static void sort(Comparable[] unsorted, boolean printFlow) {
        //确定待插入元素,从索引1处元素开始,默认0处元素为已排序
        for (int i = 1; i < unsorted.length; i++) {
            for (int j = i - 1; j >= 0; j--) {   //遍历已排序元素,i前面的都是已排序元素
                //j+1比前一个元素j小,则交换元素
                if (less(unsorted[j + 1], unsorted[j])) {
                    exchange(unsorted, j + 1, j);
                    if (printFlow && unsorted.length <= 10) {
                        System.out.println("\t" + Arrays.toString(unsorted));
                    }
                } else {
                    break;
                }
            }
            if (printFlow) {
                System.out.println("第" + i + "次插入:" + Arrays.toString(unsorted));
            }
        }
    }
}
