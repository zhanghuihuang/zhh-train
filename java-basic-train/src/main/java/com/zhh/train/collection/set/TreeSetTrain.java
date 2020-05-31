package com.zhh.train.collection.set;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * TreeSet实现了SortedSet接口
 * 1.有序的集合
 * 2.元素不能重复
 * 3.不能放置null元素
 * 4.无参构造函数默认利用TreeMap实现
 * 5.按照Comparator接口实现规则排序
 * @date : 2020/5/29 8:52 下午
 */
public class TreeSetTrain {
    public static void main(String[] args) {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("1");
        treeSet.add("2");
        List<String> p = new ArrayList<>();
        p.add("1");
        treeSet.addAll(p);
    }
}
