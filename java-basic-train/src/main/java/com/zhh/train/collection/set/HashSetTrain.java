package com.zhh.train.collection.set;

import java.util.HashSet;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * hashSet
 * 1.无序集合
 * 2.元素不能重复,只能有一个null值
 * 3.利用HashMap实现
 * @date : 2020/5/29 8:48 下午
 */
public class HashSetTrain {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(null);
        hashSet.add("1");
        for (String s : hashSet) {
            System.out.println(s);
        }
        HashSet<String> hashSet1 = new HashSet<>(10, 0.75f);
    }
}
