package com.zhh.train.algorithm.sort.uncomparable;

import java.util.ArrayList;

/**
 * @author zhanghuihuang
 * @description <pre>
 *      基数排序:
 *      按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。
 *      有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。
 * </pre>
 * @since 2020/6/19 5:24 下午
 */
public class RadixSort {
    public static void sort(Integer[] unsorted, int maxDigit) {
        //创建一个大小10的数组
        ArrayList<Integer>[] arrayLists = null;
        //遍历待排序数组
        int mod = 10, dev = 1;
        for (int i = 0; i < maxDigit; i++, mod *= 10, dev *= 10) {
            arrayLists = new ArrayList[10];
            for (int j = 0; j < unsorted.length; j++) {
                int digit = (unsorted[j] % mod) / dev;
                if (arrayLists[digit] == null) {
                    arrayLists[digit] = new ArrayList();
                }
                arrayLists[digit].add(unsorted[j]);
            }
            int index = 0;
            for (ArrayList arrayList : arrayLists) {
                if (arrayList != null && arrayList.size() > 0) {
                    for (Object o : arrayList) {
                        unsorted[index++] = (Integer) o;
                    }
                }
            }
        }
    }
}
