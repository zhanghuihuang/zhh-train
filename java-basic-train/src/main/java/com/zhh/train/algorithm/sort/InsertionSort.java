package com.zhh.train.algorithm.sort;

import java.util.Arrays;

/**
 * @author : page
 * @project : zhh-train
 * @description : 插入排序
 * 原理:把排序数组分为已排序和未排序两部分
 * 未排序的第一元素和已排序部分元素倒序遍历比较,如果比已排序元素小则交换元素直到遇到比他大的元素break
 * 初始顺序   7 6 5 4 3 2 1
 * 第1次插入  6 7 5 4 3 2 1   待插入元素6
 * 第2次排序  5 6 7 4 3 2 1   待插入元素5
 * 第3次排序  4 5 6 7 3 2 1   待插入元素4
 * 第4次排序  3 4 5 6 7 2 1   待插入元素3
 * 第5次排序  2 3 4 5 6 7 1   待插入元素2
 * 第6次排序  1 2 3 4 5 6 7   待插入元素1
 * @date : 2020/5/24 2:59 下午
 */
public class InsertionSort {

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] sort = {7, 6, 5, 4, 3, 2, 1};
        insertionSort.sort(sort);
        System.out.println(Arrays.toString(sort));
    }

    private static boolean greater(int[] sort, int k, int j) {
        return sort[k] > sort[j];
    }

    private static void exchange(int[] sort, int k, int j) {
        int temp = sort[k];
        sort[k] = sort[j];
        sort[j] = temp;
    }

    public static void sort(int[] sort) {
        //确定插入次数
        for (int i = 1; i < sort.length; i++) {
            //确定每次插入的元素
            for (int j = i; j > 0; j--) {
                if (greater(sort, j - 1, j)) {
                    exchange(sort, j, j - 1);
                } else {
                    break;
                }
            }
            System.out.println("第" + (i) + "次插入排序结果:" + Arrays.toString(sort));
        }
    }
}
