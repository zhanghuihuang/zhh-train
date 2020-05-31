package com.zhh.train.map;

import java.util.HashMap;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * HashMap jdk8开始采用数组+链表+红黑树实现,jdk7及之前采用数组+链表
 * 默认数组容量16,最大数组容量2^30
 * 加载因子0.75f,超过size超过容量*0.75就进行扩容
 * map的容量一定是2的次幂
 * @date : 2020/5/30 4:59 下午
 */
public class HashMapTrain {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>(4);
        for (int i = 0; i < 40; i++) {
            hashMap.put(i, i + "-" + i);
        }
    }
}
