package com.zhh.train.algorithm.sort;

import java.util.Arrays;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     快速排序:分治法,选定第一个元素作为分界值,比第一个元素大的放右边,比第一个元素小的放左边
 *
 * </pre>
 * @since : 2020/6/2 7:36 下午
 */
public class QuickSort {
    public static void main(String[] args) {
        Integer[] nums = {10, 2, 3, 6, 7, 8, 9, 0, 12, 2, 4, 23, 23, 45, 67};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sort(Comparable[] unsorted) {
        int lo = 0;
        int hi = unsorted.length - 1;
        sort(unsorted, lo, hi);
    }

    public static void sort(Comparable[] unsorted, int lo, int hi) {
        //校验索引有效性
        if (hi <= lo) {
            return;
        }
        //对数据进行分区,返回左子组和右子组
        int partition = partition(unsorted, lo, hi);
        //让左子组有序
        sort(unsorted, lo, partition - 1);
        //让右子组有序
        sort(unsorted, partition + 1, hi);
    }

    /**
     * 对数组lo到hi的元素进行分组,以lo位置元素为基准,大于lo位置元素的放右边,小于lo元素的放左边,并返回分区后,lo元素的索引
     * 定义两个指针,一个从左往右移动,一个从右往左移动,每移动一次,
     * 比较两个元素大小,如果右边的比左边小,则交换两个元素位置,直到两个指针相遇结束
     *
     * @param unsorted
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] unsorted, int lo, int hi) {
        //确定分界值
        Comparable key = unsorted[lo];
        //定义两个指针,分别指向待切分元素的最小索引处和最大所引处的下一个位置
        int left = lo;
        int right = hi + 1;
        //切分
        while (true) {
            //先从右往左扫描,移动right指针,找到一个比分界值小的元素,停止
            while (less(key, unsorted[--right])) {   //如果key比unsorted[right]小,继续移动--right
                if (right == lo) {
                    break;
                }
            }
            //再从左往右扫描,移动left指针,找到一个比分界值大的元素,停止
            while (less(unsorted[++left], key)) {
                if (left == hi) {
                    break;
                }
            }
            //判断如果left>=right,表示扫描完毕,结束循环,如果不是,则交换元素即可
            if (left >= right) {
                break;
            } else {
                exchange(unsorted, left, right);
            }
        }
        //交换分界值
        exchange(unsorted, lo, right);
        return right;
    }

    private static boolean less(Comparable v, Comparable l) {
        return v.compareTo(l) < 0;
    }

    private static void exchange(Comparable[] unsorted, int i, int j) {
        Comparable temp = unsorted[i];
        unsorted[i] = unsorted[j];
        unsorted[j] = temp;
    }
}
