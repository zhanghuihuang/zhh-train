package com.zhh.train.algorithm.ali;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 第三题：
 * 输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只调整引用（指针）的指向,只需要写出转换算法即可，请使用java实现其转换，并并给出时间复杂度和空间复杂度。
 * 9
 * /   \
 * 5       13
 * / \     /   \
 * 3  7     11  15
 * 转换成双向链表
 * 3=5=7=9=11=13=15。
 * @date : 2020/5/13 8:01 下午
 */
@Data
@AllArgsConstructor
public class BinaryTree<Key extends Comparable<Key>, Value> {
    Node<Key, Value> root;

    @Data
    @AllArgsConstructor
    public class Node<Key, Value> {
        private Key key;
        private Value value;
        private Node<Key, Value> left;
        private Node<Key, Value> right;
    }

    /**
     * 中序遍历
     *
     * @return
     */
    public List<Key> middleErgodic() {
        List<Key> keys = new LinkedList<>();
        this.middleErgodic(root, keys);
        return keys;
    }

    public void middleErgodic(Node<Key, Value> x, List<Key> keys) {
        if (x == null) {
            return;
        } else {
            if (x.left != null) {
                //左子节点不为空,继续找下一层左子节点
                middleErgodic(x.left, keys);
            }
            keys.add(x.getKey());
            if (x.right != null) {
                //右子节点不为空,继续找下一层右子节点
                middleErgodic(x.right, keys);
            }
        }
    }
}
