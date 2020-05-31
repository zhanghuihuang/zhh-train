package com.zhh.train.collection.set;

import java.util.LinkedHashSet;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * LinkedHashSet底层利用LinkedHashMap实现
 * 1.继承了HashSet,重写了父类构造方法,并且都显示super调用hashSet的无访问修饰符的构造
 * 2.父类的无访问修饰符的构造默认new了一个LinkedHashMap
 * @date : 2020/5/29 9:45 下午
 */
public class LinkedHashSetTrain {
    public static void main(String[] args) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(null);
        linkedHashSet.add("1");
    }
}
