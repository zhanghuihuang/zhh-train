package com.zhh.train.leetcode.day3;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素,认为null是最大元素
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date : 2020/5/12 5:22 下午
 */
class Stack1<E extends Comparable> {

    private Node<E> header;
    private int size;

    @Data
    @AllArgsConstructor
    private static class Node<E> {
        E value; //当前元素值
        E min;//最小值
        Node<E> next;
    }

    public Stack1() {

    }

    public int size() {
        return size;
    }

    public void push(E x) {
        if (x == null) {
            throw new IllegalArgumentException("不能放空元素入栈");
        }
        if (header == null) {
            header = new Node<>(x, x, null);
        } else {
            header = new Node<>(x, min(x, header.min), header);
        }
        size++;
    }

    private E min(E x, E y) {
        return x.compareTo(y) > 0 ? y : x;
    }

    public E pop() {
        if (header == null) {
            throw new RuntimeException("栈已经为空");
        }
        E e = header.value;
        header = header.next;
        size--;
        return e;
    }

    public E top() {
        if (header == null) {
            throw new RuntimeException("栈已经为空");
        }
        return header.value;
    }

    public E getMin() {
        if (header == null) {
            throw new RuntimeException("栈已经为空");
        }
        return header.min;
    }
}

public class Question1 {

    public static void main(String[] args) {
        Stack1<Integer> stack = new Stack1<>();
        stack.push(5);
        stack.push(10);
        stack.push(2);
        stack.push(14);
        stack.push(9);
        stack.push(0);
        stack.push(50);
        System.out.println("最小元素:" + stack.getMin());
        System.out.println("长度:" + stack.size());
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            System.out.println("移除:" + stack.pop());
            System.out.println("最小元素:" + stack.getMin());
        }
    }
}
