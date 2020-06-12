package com.zhh.train.algorithm.sort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 * </pre>
 * @since : 2020/6/2 4:07 下午
 */
public abstract class AbstractSort {

    /**
     * 判断a是否大于b
     *
     * @param a
     * @param b
     * @return
     */
    protected static boolean greater(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }

    /**
     * 判断a是否小于b
     *
     * @param a
     * @param b
     * @return
     */
    protected static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * 交换元素
     *
     * @param i
     * @param j
     */
    protected static void exchange(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
