package com.zhh.train.algorithm.sort;

import java.util.Arrays;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     冒泡排序:每轮以第一个元素开始,两两元素比较,大的往后挪,进行n-1轮
 * </pre>
 * @since : 2020/6/2 3:39 下午
 */
public class BubbleSort extends AbstractSort {

    public static void main(String[] args) {
        System.out.println("最终冒泡结果:" + Arrays.toString(sort(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1})));
    }

    public static Comparable[] sort(Comparable[] unsorted) {
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.unsorted = unsorted;
        //确定冒泡轮数
        //System.out.println("初始值顺序:" + Arrays.toString(bubbleSort.unsorted));
        for (int i = 1; i <= bubbleSort.unsorted.length - 1; i++) {
            //每轮冒泡
            for (int j = 0; j < bubbleSort.unsorted.length - i; j++) {
                if (bubbleSort.greater(j, j + 1)) {
                    bubbleSort.exchange(j, j + 1);
                }
            }
            //System.out.println("第" + i + "次冒泡:" + Arrays.toString(bubbleSort.unsorted));
        }
        return bubbleSort.unsorted;
    }
}
