package com.zhh.train.algorithm.sort.comparable.senior;

import com.zhh.train.algorithm.sort.AbstractSort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     快速排序:分区排序,
 *     每次选用一个基准值,对待排序数组跟基准值比较,大的放基准值右边,小的放基准值左边
 *     选数组第一个元素为基准值,然后通过左右指针移动,
 *     循环查找,先从右往左找到一个比基准值小的,再从左往右找一个比基准值大的,然后交换两个元素位置
 *     知道右指针等于左指针,跳出循环
 *     然后把基准值和右指针所在位置元素交换
 * </pre>
 * @since : 2020/6/13 12:48 下午
 */
public class QuickSort extends AbstractSort {
    public static void sort(Comparable[] unsorted) {
        if (unsorted.length <= 1) {
            return;
        }
        sort(unsorted, 0, unsorted.length - 1);
    }

    /**
     * 对数组lo到hi索引的元素进行排序
     *
     * @param unsorted
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] unsorted, int lo, int hi) {
        //判断lo和hi的合法性
        if (hi <= lo) {
            return;
        }
        //对数组进行分区,并获取分区后,基准元素的索引
        int partition = partition(unsorted, lo, hi);
        //对分区后的左右两个子数组分别再排序
        sort(unsorted, lo, partition - 1);
        sort(unsorted, partition + 1, hi);
    }

    /**
     * 对数组lo到hi的元素进行按基准值分区,并返回分区后,基准元素的所在位置索引
     *
     * @param unsorted
     * @param lo
     * @param hi
     * @return
     */
    public static int partition(Comparable[] unsorted, int lo, int hi) {
        //确定基准值
        Comparable base = unsorted[lo];
        //定义左右指针
        int left = lo, right = hi + 1;
        //移动左右指针,先右指针找到一个比基准值小的,再左指针找到一个比基准值大的,交换
        while (true) {
            while (greater(unsorted[--right], base)) { //如果右指针元素比基准值大,就继续找,直到找到小的
                if (right == left) {
                    break;
                }
            }
            while (greater(base, unsorted[++left])) { //如果基准值比左指针元素大,就继续找,直到找到大的
                if (right == left) {
                    break;
                }
            }
            if (right <= left) {
                break;
            } else {
                //交换
                exchange(unsorted, left, right);
            }
        }
        //调整基准值索引,并返回调整后的索引
        exchange(unsorted, lo, right);
        return right;
    }
}
