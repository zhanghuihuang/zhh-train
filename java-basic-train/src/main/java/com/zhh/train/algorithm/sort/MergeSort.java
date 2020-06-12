package com.zhh.train.algorithm.sort;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     归并排序:将数组进行分解成最小单元,利用辅助数组,把最小单元比较大小后,合并到辅助数组
 * </pre>
 * @since : 2020/6/11 2:01 下午
 */
public class MergeSort extends AbstractSort {
    private static Comparable[] tempArr;

    public static void sort(Comparable[] unsorted) {
        tempArr = new Comparable[unsorted.length];
        sort(unsorted, 0, unsorted.length - 1);
        tempArr = null;
    }

    /**
     * 排序索引lo到hi处的元素
     *
     * @param unsorted
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] unsorted, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        //将数组从中间拆分为两个数组分别排序
        sort(unsorted, lo, mid);
        sort(unsorted, mid + 1, hi);
        //对排序完后的lo到hi元素进行归并
        merge(unsorted, lo, mid, hi);
    }

    /**
     * 对索引lo到hi处的元素归并
     *
     * @param unsorted
     * @param lo
     * @param mid
     * @param hi
     */
    private static void merge(Comparable[] unsorted, int lo, int mid, int hi) {
        //归并排序,是在归并的时候,对归并的数据进行排序
        //定义两个索引指针p1,p2分别指向要归并的两个子数组的开始位置,定义一个索引指针指向辅助数组的lo位置
        int p1 = lo, p2 = mid + 1, temp = lo;
        while (p1 <= mid && p2 <= hi) {
            if (greater(unsorted[p1], unsorted[p2])) {
                tempArr[temp++] = unsorted[p2++];
            } else {
                tempArr[temp++] = unsorted[p1++];
            }
        }
        //把p1或p2剩下没遍历的元素,依次填入临时数组
        while (p1 <= mid) {
            tempArr[temp++] = unsorted[p1++];
        }
        while (p2 <= hi) {
            tempArr[temp++] = unsorted[p2++];
        }
        //复制数组元素到原始数组
        System.arraycopy(tempArr, lo, unsorted, lo, hi - lo + 1);
    }
}
