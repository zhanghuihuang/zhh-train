package com.zhh.train.leetcode.day3;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 用两个队列来模拟栈
 * 队列的特性:先进先出,队尾插入,队首删除
 * ---------------
 * -> 4  3  2  1 |  ->
 * ---------------
 * 栈的特性:后进先出,队尾插入,队尾删除
 * ---------------
 * <-> 4  3  2  1|             4 3 2 1
 * ---------------
 * @date : 2020/5/13 11:27 上午
 */

class TwoQueueStack<E> {
    Queue<E> q1;
    Queue<E> q2;

    public TwoQueueStack(Queue<E> q1, Queue<E> q2) {
        this.q1 = q1;
        this.q2 = q2;
    }

    public void push(E x) {
        if (q1.isEmpty()) {
            q2.offer(x);
        } else {
            q1.offer(x);
        }
    }

    public E pop() {
        if (q1.isEmpty()) {
            while (q2.size() > 0) {
                if (q2.size() == 1) {
                    //队尾元素,即栈顶元素
                    return q2.poll();
                }
                // 把q2元素,依次入队到q1
                q1.offer(q2.poll());
            }
        } else {
            while (q1.size() > 0) {
                if (q1.size() == 1) {
                    //队尾元素,即栈顶元素
                    return q1.poll();
                }
                // 把q1元素,依次入队到q2
                q2.offer(q1.poll());
            }
        }
        return null;
    }

    public int size() {
        return q1.isEmpty() ? q2.size() : q1.size();
    }
}

public class Question2 {

    public static void main(String[] args) {
        arrayDeque();
    }

    private static void arrayDeque() {
        TwoQueueStack<Integer> stack = new TwoQueueStack<>(new ArrayDeque<>(), new ArrayDeque<>());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    private static void linkListQueue() {
        TwoQueueStack<Integer> stack = new TwoQueueStack<>(new LinkedList<>(), new LinkedList<>());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
