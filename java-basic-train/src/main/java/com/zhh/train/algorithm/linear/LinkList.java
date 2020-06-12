package com.zhh.train.algorithm.linear;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.StringJoiner;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     单向链表
 * </pre>
 * @since : 2020/6/11 4:08 下午
 */
public class LinkList<T> {
    private Node head;
    private int size;

    public LinkList() {
        head = new Node(null, null);
        size = 0;
    }

    public void clear() {
        head.next = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(T t) {
        Node latest = head;
        while (latest.next != null) {
            latest = latest.next;
        }
        latest.next = new Node(t, null);
        size++;
    }

    public void insert(int i, T t) {
        if (i > size - 1) {
            throw new IllegalArgumentException("超出链表长度");
        }
        Node pre = head;
        for (int j = 0; j < i; j++) {
            pre = pre.next;
        }
        pre.next = new Node(t, pre.next);
        size++;
    }

    public T replace(int i, T t) {
        if (i > size - 1) {
            throw new IllegalArgumentException("超出链表长度");
        }
        Node cur = head;
        for (int j = 0; j <= i; j++) {
            cur = cur.next;
        }
        T curData = cur.getData();
        cur.setData(t);
        return curData;
    }

    public T get(int i) {
        if (i > size - 1) {
            throw new IllegalArgumentException("超出链表长度");
        }
        Node cur = head;
        for (int j = 0; j <= i; j++) {
            cur = cur.next;
        }
        return cur.getData();
    }

    public T remove(int i) {
        if (i > size - 1) {
            throw new IllegalArgumentException("超出链表长度");
        }
        Node pre = head;
        for (int j = 0; j < i; j++) {
            pre = pre.next;
        }
        Node removeNode = pre.next;
        pre.next = removeNode.next;
        size--;
        return removeNode.getData();
    }

    public boolean remove(T t) {
        int indexOf = indexOf(t);
        if (indexOf > -1) {
            remove(indexOf);
            return true;
        }
        return false;
    }

    public int indexOf(T t) {
        Node cur = head;
        int count = 0;
        while (cur.next != null) {
            cur = cur.next;
            if (t == null && cur.getData() == null) {
                return count;
            } else if (t != null && t.equals(cur.getData())) {
                return count;
            }
            count++;
        }
        return -1;
    }

    @Data
    @AllArgsConstructor
    private class Node {
        private T data;
        private Node next;
    }

    @Override
    public String toString() {
        if (size > 0) {
            Node cur = head;
            StringJoiner stringJoiner = new StringJoiner("->");
            while (cur.next != null) {
                cur = cur.next;
                stringJoiner.add(cur.getData() == null ? null : cur.getData().toString());
            }
            return stringJoiner.toString();
        }
        return "[]";
    }
}
