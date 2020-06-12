package com.zhh.train.algorithm.sort;

import java.util.Arrays;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     冒泡排序:每一轮进行两两比较,大的元素往后交换冒泡,共进行n-1轮
 *     7654321  -初始顺序
 *     6543217  -第一轮冒泡
 *     5432167
 *     4321567
 *     3214567
 *     2134567
 *     1234567
 * </pre>
 * @since : 2020/6/11 9:21 上午
 */
public class BubbleSort extends AbstractSort {

    public static void sort(Comparable[] unsorted) {
        sort(unsorted, false);
    }

    public static void sort(Comparable[] unsorted, boolean printFlow) {
        //确定冒泡轮数,每一轮都是从0索引位置开始,往后两两比较
        //每一轮产生一个最大值,放到最后面,因此下一轮少一个元素参加冒泡
        boolean printDetail = unsorted.length <= 10;
        for (int i = 0; i < unsorted.length - 1; i++) {
            //每轮参加冒泡的元素
            for (int j = 0; j < unsorted.length - 1 - i; j++) {
                if (greater(unsorted[j], unsorted[j + 1])) {
                    exchange(unsorted, j, j + 1);
                }
                if (printFlow && printDetail) {
                    System.out.println("\t" + Arrays.toString(unsorted));
                }
            }
            if (printFlow) {
                System.out.println("第" + (i + 1) + "次冒泡:" + Arrays.toString(unsorted));
            }
        }
    }
}
