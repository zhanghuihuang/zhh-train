package com.zhh.train.collection.list;

import java.util.Stack;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * Stack开始于jdk1.0,集成vector
 * 扩展了push,pop,peek方法
 * @date : 2020/5/24 4:17 下午
 */
public class StackTrain {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        System.out.println(stack.peek());  //查看此堆栈顶部的对象，而不从堆栈中删除它。
        stack.pop();
        System.out.println(stack.peek());  //查看此堆栈顶部的对象，而不从堆栈中删除它。
    }
}
