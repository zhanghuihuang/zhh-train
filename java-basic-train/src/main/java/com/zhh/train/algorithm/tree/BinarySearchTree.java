package com.zhh.train.algorithm.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : page
 * @project : zhh-train
 * @description : 二叉查找树
 * 特性:
 * 1.除根结点外,一个结点只有一个父结点
 * 2.每个结点至多有两个子结点
 * 3.父结点比左子结点大,比右子结点小
 * 采用链表结构实现
 * @date : 2020/5/19 8:05 下午
 */
@Data
public class BinarySearchTree<Key extends Comparable, Value> {

    private Node root;
    private int size;

    @Data
    @AllArgsConstructor
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
    }

    //插入
    public void put(Key key, Value value) {
        root = this.put(root, key, value);
    }

    /**
     * 往子树tree添加子结点,并返回添加后的新树
     *
     * @param tree
     * @param key
     * @param value
     */
    private Node put(Node tree, Key key, Value value) {
        if (tree == null) {
            //如果subTree为空,表示是空树
            tree = new Node(key, value, null, null);
        } else {
            //如果子树不为空,比较key和子树key的大小,比它小,则往左子树继续找,比它大则往右子树找,相等则替换掉value值
            int result = key.compareTo(tree.getKey());
            if (result > 0) {
                //往右子树找
                tree.right = put(tree.getRight(), key, value);
            } else if (result < 0) {
                //往左子树
                tree.left = put(tree.getLeft(), key, value);
            } else {
                //替换掉值
                tree.setValue(value);
            }
        }
        //树大小加1
        size++;
        return tree;
    }

    //查找
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 在结点node下查找key的值
     *
     * @param tree
     * @param key
     * @return
     */
    private Value get(Node tree, Key key) {
        if (tree == null) {
            return null;
        }
        int result = key.compareTo(tree.getKey());
        if (result > 0) {
            return get(tree.getRight(), key);
        } else if (result < 0) {
            return get(tree.getLeft(), key);
        } else {
            return tree.getValue();
        }
    }

    /**
     * 删除一个节点
     * 删除某个节点,就从这个结点的右子树找一个最小值结点顶替
     * 如果右子树有空,则左子树顶上
     *
     * @param key
     */
    public void delete(Key key) {
        delete(root, key);
    }

    /**
     * 删除子树下的key,并返回删除后的新树
     *
     * @param tree
     * @param key
     * @return
     */
    private Node delete(Node tree, Key key) {
        if (tree == null) {
            return null;
        }
        int result = key.compareTo(tree.key);
        if (result > 0) {
            tree.right = delete(tree.right, key);
        } else if (result < 0) {
            tree.left = delete(tree.left, key);
        } else {
            //长度减小
            size--;
            if (tree.right == null) {
                return tree.left;
            }
            if (tree.left == null) {
                return tree.right;
            }
            //先找到右子树的最小节点
            Node minNode = tree.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }
            //删除右子树中的最小结点
            Node n = tree.right;
            while (n.left != null) {
                if (n.left.left == null) {
                    n.left = null;
                } else {
                    n = n.left;
                }
            }
            //让最小结点的左右子树分别指向删除结点的左右子树
            minNode.right = tree.right;
            minNode.left = tree.left;
            //让删除结点的父结点指向最小结点
            tree = minNode;

        }
        return tree;
    }

    //获取树长度
    public int size() {
        return size;
    }
}
