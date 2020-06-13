package com.zhh.train.algorithm.sort.senior;

import com.zhh.train.algorithm.sort.AbstractSort;

import java.util.Arrays;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     希尔排序:插入排序的升级版,也称缩小增量排序,插入排序就是初始增量为1的希尔排序
 *     即n处元素和n+h,n+2h,n+3h,...,处元素做插入排序
 *     步骤:
 *     1. 确定一个初始增量,通常取排序数组的1/2长度
 *     2. 每个增量值进行一次插入排序,然后缩小增量值为原来的1/2
 *     3. 每次待插入元素就是从增量位置索引开始,然后跟每个增量单位长度的索引元素比较插入
 *     比如  10 9 8 7 6 5 4 3 2 1
 *     初始增量是5
 *     第二次增量2:
 *          从8开始,8跟10比较插入后,8 9 10 7 6 5 4 3 2 1
 *                 7跟9比较插入后,8 7 10 9 6 5 4 3 2 1
 *                 6跟10,8比较插入后,6 7 8 9 10 5 4 3 2 1
 *                 5跟9,7比较插入后,6 5 8 7 10 9 4 3 2 1
 *                 依次类推...
 *     第三次增量1
 * </pre>
 * @since : 2020/6/13 8:08 上午
 */
public class ShellSort extends AbstractSort {
    public static void sort(Comparable[] unsorted, boolean printFlow) {
        //确定初始增量
        int increment = unsorted.length / 2;
        //循环缩减增量
        while (increment >= 1) {
            //每次按增量进行一次插入排序
            //确定待插入元素,从增量索引处开始后面的元素都是待插入元素
            for (int i = increment; i < unsorted.length; i++) {
                //倒序遍历已排序元素,每个待插入元素跟对应增量的已插入元素比较,进行插入排序
                for (int j = i - increment; j >= 0; j -= increment) {
                    //如果j+increment元素比j小,则交换元素
                    if (less(unsorted[j + increment], unsorted[j])) {
                        exchange(unsorted, j + increment, j);
                        if (printFlow && unsorted.length <= 10) {
                            System.out.println("\t" + Arrays.toString(unsorted));
                        }
                    } else {
                        break;
                    }
                }
                if (printFlow) {
                    System.out.println("增量" + increment + "的第" + (i - increment + 1) + "次插入:" + Arrays.toString(unsorted));
                }
            }
            System.out.println("增量" + increment + "的插入排序结果:" + Arrays.toString(unsorted));
            increment = increment / 2;
        }
    }
}
