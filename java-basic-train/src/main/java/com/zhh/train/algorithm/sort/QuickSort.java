package com.zhh.train.algorithm.sort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     快速排序:也是分治理论
 *     第一步:选取一个分界值,把比分界值小的放分界值左边,比分界值大的放分界值右边
 *     第二步:对左子组和右子组分别再选取分界值,重复第一步
 *     我们每次选取子数组的第一个元素作为分界值
 *     定义两个指针,左指针从分界值所引处开始,右指针从数组长度的下一个值开始
 *     先从右往左找到一个比分界值小的元素
 *     再从左
 *     直到两个指针相遇,交换相遇位置元素和分界值元素
 * </pre>
 * @since : 2020/6/11 2:49 下午
 */
public class QuickSort extends AbstractSort {
    public static void sort(Comparable[] unsorted) {
        sort(unsorted, 0, unsorted.length - 1);
    }

    /**
     * 排序索引lo到hi处的元素
     *
     * @param unsorted
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] unsorted, int lo, int hi) {
        //判断hi不能大于lo
        if (hi <= lo) {
            return;
        }
        //对lo到hi的元素按分界值进行分区
        int partition = partition(unsorted, lo, hi);
        //对分区后的左子组和右子组进行排序
        sort(unsorted, lo, partition - 1);
        sort(unsorted, partition + 1, hi);
    }

    /**
     * 对索引lo到hi处的元素进行分区,并返回分界值处的索引
     *
     * @param unsorted
     * @param lo
     * @param hi
     */
    private static int partition(Comparable[] unsorted, int lo, int hi) {
        //选取lo处的元素为分界值
        Comparable boundary = unsorted[lo];
        //定义两个指针
        int left = lo, right = hi + 1;
        while (true) {
            while (less(boundary, unsorted[--right])) {
                if (right == left) {
                    break;
                }
            }
            while (less(unsorted[++left], boundary)) {
                if (right == left) {
                    break;
                }
            }
            if (left == right) {
                break;
            } else {
                exchange(unsorted, left, right);
            }
        }
        //交换分界值和右指针指向的值
        exchange(unsorted, lo, right);
        return right;
    }
}
