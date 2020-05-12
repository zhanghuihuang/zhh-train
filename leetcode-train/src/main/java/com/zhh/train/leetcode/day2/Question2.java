package com.zhh.train.leetcode.day2;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * @date : 2020/5/10 10:05 上午
 */
public class Question2 {
    public static void main(String[] args) {
        //[2,4,3]
        //[5,6,4]
        //[9]
        //[1,9,9,9,9,9,9,9,9,9]
        ListNode l1 = ListNode.toListNode(9);
        ListNode l2 = ListNode.toListNode(99999991);
        addTwoNumbers(l1, l2);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int i = ListNode.toNum(l1);
        int j = ListNode.toNum(l2);
        int sum = i + j;
        ListNode listNode = ListNode.toListNode(sum);
        System.out.println(listNode);
        return listNode;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public static int toNum(ListNode listNode) {
            int ws = 1;
            int num = 0;
            ListNode temp = listNode;
            while (temp != null) {
                num += temp.val * ws;
                ws = ws * 10;
                temp = temp.next;
            }
            return num;
        }

        public static ListNode toListNode(int num) {
            if (num < 0) {
                throw new IllegalArgumentException("请输入非负数");
            }
            if (num == 0) {
                return new ListNode(0, null);
            }
            char[] chars = String.valueOf(num).toCharArray();
            ListNode root = new ListNode(Character.getNumericValue(chars[chars.length - 1]), null);
            ListNode temp = root;
            for (int i = chars.length - 2; i >= 0; i--) {
                temp.next = new ListNode(Character.getNumericValue(chars[i]), null);
                temp = temp.next;
            }
            return root;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            ListNode temp = this;
            while (temp != null) {
                stringBuilder.append(temp.val);
                if (temp.next != null) {
                    stringBuilder.append(" -> ");
                }
                temp = temp.next;
            }
            return stringBuilder.toString();
        }
    }
}
