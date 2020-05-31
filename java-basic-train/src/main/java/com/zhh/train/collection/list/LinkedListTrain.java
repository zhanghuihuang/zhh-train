package com.zhh.train.collection.list;

import java.util.LinkedList;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 1.继承抽象类AAbstractSequentialList,Sequential=相继的
 * 2.实现了list接口和Deque双向队列接口
 * 3.数据结构:双向链表
 * 4.查找效率不高,需要依次遍历所有元素
 * 5.
 * @date : 2020/5/23 12:12 下午
 */
public class LinkedListTrain {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        /**
         * 插入首尾
         */
        linkedList.add("1");  //默认插入尾部
        /**
         * 创建一个newNode的next引用指向firstNode
         * first引用指向newNode
         * 如果firstNode为空,则lastNode引用指向newNode
         * 如果firstNode不为空,则firstNode的prev引用指向newNode
         */
        linkedList.addFirst("2");
        linkedList.addLast("3");
        linkedList.addLast("4");
        linkedList.addLast("5");

        /**
         * 指定位置插入
         * 如果插入位置等于size,则调用linkLast
         * 如果不等,根据index跟size/2比较,如果大于,从后面往前找,如果小于,从前往后找
         */
        linkedList.add(3, "3");
        /**
         * 删除
         */
        linkedList.remove();      //默认移除首部,等价于removeFirst
        linkedList.remove("1");//从首部开始找节点,找到就删除,等价于removeFirstOccurrence
        linkedList.remove(1);
        linkedList.removeFirst();  //删除首节点
        linkedList.removeLast();
        linkedList.removeLastOccurrence("1");//从尾部开始找节点,找到就删除

        /**
         * 队列方法
         */
        linkedList.offer("1");
        //System.out.println(linkedList.poll());
        System.out.println(linkedList.pop());
    }
}
