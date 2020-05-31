package com.zhh.train.leetcode.day3;

import java.util.Stack;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 使用两个栈来模拟队列
 * @date : 2020/5/13 11:40 上午
 */
class TwoStackQueue<E> {
    private Stack<E> in = new Stack<>();
    private Stack<E> out = new Stack<>();

    public void offer(E e) {
        in.add(e);
    }

    public E poll() {
        if (out.size() > 0) {
            return out.pop();
        }
        while (in.size() > 0) {
            out.push(in.pop());
        }
        if (out.size() > 0) {
            return out.pop();
        } else {
            return null;
        }
    }
}

public class Question3 {

    public static void main(String[] args) {
        TwoStackQueue<Integer> queue = new TwoStackQueue<>();
    }

}
