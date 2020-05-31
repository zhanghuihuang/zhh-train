package com.zhh.train.algorithm.tree.test;

import com.zhh.train.algorithm.tree.BinarySearchTree;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/5/20 2:53 下午
 */
public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> binarySearchTree = new BinarySearchTree<>();
        int[] nums = {34, 23, 44, 12, 25, 88, 100, 48, 37};
        for (int num : nums) {
            binarySearchTree.put(num, num);
        }
        System.out.println(binarySearchTree.size());
        for (int num : nums) {
            binarySearchTree.delete(num);
        }
        System.out.println(binarySearchTree);
    }
}
