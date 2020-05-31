package com.zhh.train.algorithm.sort;

import java.util.Arrays;

/**
 * @author : page
 * @project : zhh-train
 * @description : 选择排序
 * 原理:把排序数组分为已排序和未排序两部分
 * 每次排序都是假设未排序部分的第一个为最小值索引,然后依次往后比,如果遇到更小的,则更换最小索引
 * 直到没有元素可比,交换最小索引和未排序第一个元素位置
 * 初始顺序   2 3 4 5 6 7 1
 * 第1次排序  1 3 4 5 6 7 2   起始min=1
 * 第2次排序  1 2 4 5 6 7 3   起始min=2
 * 第3次排序  1 2 3 5 6 7 4   起始min=3
 * 第4次排序  1 2 3 4 6 7 5   起始min=4
 * 第5次排序  1 2 3 4 5 7 6   起始min=5
 * 第6次排序  1 2 3 4 5 6 7   起始min=6
 * @date : 2020/5/19 10:04 下午
 */
public class SelectionSort {

    public static void main(String[] args) {
        SelectionSort bubbleSort = new SelectionSort();
        int[] sort = {2, 3, 4, 5, 6, 7, 1};
        bubbleSort.sort(sort);
        System.out.println(Arrays.toString(sort));
    }

    private boolean greater(int[] sort, int k, int j) {
        return sort[k] > sort[j];
    }

    private void exchange(int[] sort, int k, int j) {
        int temp = sort[k];
        sort[k] = sort[j];
        sort[j] = temp;
    }

    public void sort(int[] sort) {
        //确定排序次数sort.length-1
        for (int i = 0; i < sort.length - 1; i++) {
            int minIndex = i;
            //确定参加排序的元素比较
            for (int j = i + 1; j < sort.length; j++) {
                if (greater(sort, minIndex, j)) {
                    //找到最小元素索引
                    minIndex = j;
                }
            }
            //交换最小元素和未排序第一个元素
            exchange(sort, i, minIndex);
            System.out.println("第" + (i + 1) + "次排序结果:" + Arrays.toString(sort));
        }
    }
}
