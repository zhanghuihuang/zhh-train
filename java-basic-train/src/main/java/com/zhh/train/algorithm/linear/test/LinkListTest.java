package com.zhh.train.algorithm.linear.test;

import com.zhh.train.algorithm.linear.LinkList;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 * </pre>
 * @since : 2020/6/12 1:19 下午
 */
public class LinkListTest {
    public static void main(String[] args) {
        LinkList<String> linkList = new LinkList<>();
        for (int i = 0; i < 10; i++) {
            linkList.insert(String.valueOf(i));
        }
        System.out.println(linkList);
        linkList.insert(3, "kk");
        System.out.println(linkList);
        System.out.println(linkList.get(3));
        System.out.println(linkList.indexOf("kk"));
        System.out.println(linkList.indexOf("9"));
        linkList.insert(null);
        System.out.println(linkList);
        linkList.insert(10, "新9");
        System.out.println(linkList);
        linkList.remove(10);
        System.out.println(linkList);
        linkList.remove("kk");
        System.out.println(linkList);
        linkList.remove(null);
        System.out.println(linkList);
    }
}
