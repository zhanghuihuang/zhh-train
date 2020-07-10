package com.zhh.train.algorithm.sort.uncomparable;

/**
 * @author zhanghuihuang
 * @description <pre>
 *      计数排序:适合场景就是排序数值范围比较小
 *      比如:给一堆人的身高数据,要求按身高从小到大排序
 *      给一堆人的高考成绩,算某个成绩排在第几名
 *      计数排序是一个稳定的排序算法。
 *      当输入的元素是 n 个 0到 k 之间的整数时，时间复杂度是O(n+k)，空间复杂度也是O(n+k)，其排序速度快于任何比较排序算法。
 *      当k不是很大并且序列比较集中时，计数排序是一个很有效的排序算法。
 * </pre>
 * @since 2020/6/19 5:22 下午
 */
public class CountingSort {
    public static void sort(Integer[] unsorted, int max) {
        //创建max+1个桶
        int[] counter = new int[max + 1];
        //遍历未排序数组,跟下标对应的桶,计数加1
        for (int i = 0; i < unsorted.length; i++) {
            counter[unsorted[i]]++;
        }
        //根据计数和下标值,取出数据,依次放入排序数组
        int index = 0;
        for (int i = 0; i < counter.length; i++) {
            while (counter[i] > 0) {
                unsorted[index++] = i;
                counter[i]--;
            }
        }
    }
}
