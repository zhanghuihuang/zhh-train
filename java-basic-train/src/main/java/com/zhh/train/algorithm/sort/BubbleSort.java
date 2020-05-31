package com.zhh.train.algorithm.sort;

import java.util.Arrays;

/**
 * @author : page
 * @project : zhh-train
 * @description : 冒泡排序
 * 原理:元素两两比较,如果前一个元素比后一个元素大,则两者交换位置
 * 初始顺序   6 5 4 3 2 1
 * 第1次冒泡  5 4 3 2 1 6
 * 第2次冒泡  4 3 2 1 5 6
 * 第3次冒泡  3 2 1 4 5 6
 * 第4次冒泡  2 1 3 4 5 6
 * 第5次冒泡  1 2 3 4 5 6
 * @date : 2020/5/19 10:04 下午
 */
public class BubbleSort {

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] sort = {6, 5, 4, 3, 2, 1};
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
        //确定冒泡次数
        for (int i = sort.length - 1; i > 0; i--) {  //(N-1)+(N-2)+...+2+1 
            //确定参加冒泡的元素
            for (int j = 0; j < i; j++) {           //(N-1)+(N-2)+...+2+1
                if (greater(sort, j, j + 1)) {
                    exchange(sort, j, j + 1);
                }
            }
        }
    }
}
