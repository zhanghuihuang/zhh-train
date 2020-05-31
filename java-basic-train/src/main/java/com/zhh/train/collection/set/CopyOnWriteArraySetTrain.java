package com.zhh.train.collection.set;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 持有一个CopyOnWriteArrayList对象
 * 调用它的addIfAbsent来保证对象不重复,不在时再添加
 * @date : 2020/5/30 4:30 下午
 */
public class CopyOnWriteArraySetTrain {
    public static void main(String[] args) {
        CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        copyOnWriteArraySet.add("a");
    }
}
