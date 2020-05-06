package com.zhh.train.algorithm.tree.test;


import com.zhh.train.algorithm.tree.BinaryTree;

import java.util.List;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/5/4 11:56 上午
 */
public class BinaryTreeTest {
    public static void main(String[] args) {
        testMaxDepth();
    }

    private static void testMaxDepth() {
        Integer[] nums = new Integer[]{34, 23, 44, 12, 25, 88, 100, 70, 40, 35, 38, 42, 50, 46, 45, 75, 87};
        BinaryTree<Integer, String> bt = new BinaryTree<Integer, String>();
        for (int i = 0; i < nums.length; i++) {
            bt.put(nums[i], String.valueOf(i));
        }
        System.out.println("最大深度算法1:" + bt.maxDepth());
        System.out.println("最大深度算法2:" + bt.maxDepth1());
    }

    private static void testErgodic() {
        Integer[] nums = new Integer[]{50, 25, 75, 10, 35, 60, 80, 5, 15, 30, 40};
        String[] strs = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        BinaryTree<Integer, String> bt = new BinaryTree<Integer, String>();
        for (int i = 0; i < nums.length; i++) {
            bt.put(nums[i], strs[i]);
        }
        List<Integer> preErgodic = bt.preErgodic();
        String preExpect = "ABDHIEJKCFG";
        StringBuilder preSb = new StringBuilder();
        for (Integer integer : preErgodic) {
            preSb.append(bt.get(integer));
        }
        System.out.println("前序遍历结果:" + preSb.toString() + "," + preExpect.equals(preSb.toString()));
        List<Integer> middleErgodic = bt.middleErgodic();
        String middleExpect = "HDIBJEKAFCG";
        StringBuilder middleSb = new StringBuilder();
        for (Integer integer : middleErgodic) {
            middleSb.append(bt.get(integer));
        }
        System.out.println("中序遍历结果:" + middleSb.toString() + "," + middleExpect.equals(middleSb.toString()));
        List<Integer> postErgodic = bt.postErgodic();
        String postExpect = "HIDJKEBFGCA";
        StringBuilder postSb = new StringBuilder();
        for (Integer integer : postErgodic) {
            postSb.append(bt.get(integer));
        }
        System.out.println("后序遍历结果:" + postSb.toString() + "," + postExpect.equals(postSb.toString()));
        List<Integer> layerErgodic = bt.layerErgodic();
        String layerExpect = "ABCDEFGHIJK";
        StringBuilder layerSb = new StringBuilder();
        for (Integer integer : layerErgodic) {
            layerSb.append(bt.get(integer));
        }
        System.out.println("层序遍历结果实现1:" + layerSb.toString() + "," + layerExpect.equals(layerSb.toString()));
        List<Integer> layerErgodic1 = bt.layerErgodic1();
        StringBuilder layerSb1 = new StringBuilder();
        for (Integer integer : layerErgodic1) {
            layerSb1.append(bt.get(integer));
        }
        System.out.println("层序遍历结果实现2:" + layerSb1.toString() + "," + layerExpect.equals(layerSb1.toString()));
    }

    private static void testMinMaxKey() {
        Integer[] nums = new Integer[]{34, 23, 44, 12, 25, 88, 100, 70, 40, 35, 38, 42, 50, 46, 45, 75, 87};
        BinaryTree<Integer, String> bt = new BinaryTree<Integer, String>();
        for (Integer num : nums) {
            bt.put(num, "我的key是:" + num.toString());
        }
        System.out.println("创建一个二叉树,结点数:" + bt.getSize());
        System.out.println("最小key:" + bt.minKey());
        System.out.println("最大key:" + bt.maxKey());
    }

    /**
     * 删除结点的右子树的最小结点没有右子树情况
     */
    private static void testCRUD1() {
        Integer[] nums = new Integer[]{34, 23, 44, 12, 25, 88, 100, 70, 40, 35, 38, 42, 50, 46, 45, 75, 87};
        testCRUD(nums);
    }

    /**
     * 删除结点的右子树的最小结点有右子树情况
     */
    private static void testCRUD2() {
        Integer[] nums = new Integer[]{34, 23, 44, 12, 25, 88, 100, 70, 40, 35, 38, 42, 75, 87};
        testCRUD(nums);
    }

    private static void testCRUD(Integer[] nums) {
        BinaryTree<Integer, String> bt = new BinaryTree<Integer, String>();
        for (Integer num : nums) {
            bt.put(num, "我的key是:" + num.toString());
        }
        System.out.println("创建一个二叉树,结点数:" + bt.getSize());
        for (Integer num : nums) {
            String value = bt.get(num);
            System.out.println("删除前,查找key为" + num + "的value值:" + value);
        }
        bt.remove(44);
        System.out.println("删除结点44后的节点数:" + bt.getSize());
        for (Integer num : nums) {
            String value = bt.get(num);
            System.out.println("删除后,查找key为" + num + "的value值:" + value);
        }
    }
}
