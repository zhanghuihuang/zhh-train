package com.zhh.train.algorithm.sort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 * </pre>
 * @since : 2020/6/2 4:07 下午
 */
public abstract class AbstractSort {
    protected Comparable[] unsorted;

    /**
     * 判断索引i处元素是否大于索引j处元素
     *
     * @param i
     * @param j
     * @return
     */
    protected boolean greater(int i, int j) {
        return this.unsorted[i].compareTo(this.unsorted[j]) > 0;
    }

    /**
     * 交换元素
     *
     * @param i
     * @param j
     */
    protected void exchange(int i, int j) {
        Comparable temp = this.unsorted[i];
        this.unsorted[i] = this.unsorted[j];
        this.unsorted[j] = temp;
    }
}
