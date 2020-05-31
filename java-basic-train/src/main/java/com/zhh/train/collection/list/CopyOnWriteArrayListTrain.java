package com.zhh.train.collection.list;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : page
 * @project : zhh-train
 * @description : 线程安全的数组集合,比vector效率高
 * 利用可重入锁+数组复制
 * 插入时,加锁,复制旧的数组并扩容1,把添加元素放到最后一个位置
 * @date : 2020/5/24 4:36 下午
 */
public class CopyOnWriteArrayListTrain {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("1");
        copyOnWriteArrayList.addIfAbsent("1");
        String ele = copyOnWriteArrayList.get(0);
        copyOnWriteArrayList.remove(1);
    }
}
