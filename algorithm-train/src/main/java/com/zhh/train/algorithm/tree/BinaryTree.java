package com.zhh.train.algorithm.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/5/3 6:00 下午
 */
@Data
public class BinaryTree<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root;
    private int size;

    @Data
    @AllArgsConstructor
    public class Node<Key, Value> {
        private Key key;
        private Value value;
        private Node<Key, Value> left;
        private Node<Key, Value> right;
    }

    /**
     * 添加一个键值对
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    /**
     * 给指定子树x上添加一个键值对
     * 并返回添加后的新树
     *
     * @param x
     * @param key
     * @param value
     * @return
     */
    public BinaryTree.Node put(Node<Key, Value> x, Key key, Value value) {
        //如果x子树为null,
        if (x == null) {
            size++;
            return new Node(key, value, null, null);
        } else {
            int compare = key.compareTo(x.getKey());
            if (compare > 0) {
                // key大于x的key,则继续查找x的右子树
                Node newRight = put(x.getRight(), key, value);
                x.setRight(newRight);
            } else if (compare < 0) {
                // key小于x的key,则继续查找x的左子树
                Node newLeft = put(x.getLeft(), key, value);
                x.setLeft(newLeft);
            } else {
                // key等于x的key,则替换x的value值
                x.value = value;
            }
        }
        return x;
    }

    /**
     * 根据Key查找对应的Value值
     *
     * @param key
     * @return
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 查找执行x子树下Key对应的Value
     *
     * @param x
     * @param key
     * @return
     */
    public Value get(Node<Key, Value> x, Key key) {
        if (x == null) {
            return null;
        } else {
            int compare = key.compareTo(x.getKey());
            if (compare > 0) {
                return get(x.getRight(), key);
            } else if (compare < 0) {
                return get(x.getLeft(), key);
            } else {
                return x.value;
            }
        }
    }

    /**
     * 根据Key查找删除对应的结点
     *
     * @param key
     * @return
     */
    public void remove(Key key) {
        root = remove(root, key);
    }

    /**
     * 根据Key查找删除对应的结点,并返回删除后的新树
     *
     * @param x
     * @param key
     * @return
     */
    public BinaryTree.Node remove(Node<Key, Value> x, Key key) {
        if (x == null) {
            return null;
        } else {
            int compare = key.compareTo(x.getKey());
            if (compare > 0) {
                x.right = remove(x.right, key);
            } else if (compare < 0) {
                x.left = remove(x.left, key);
            } else {
                size--;
                //真正完成删除逻辑,要删除的就是x结点
                if (x.right == null) {
                    return x.left;
                }
                if (x.left == null) {
                    return x.right;
                }
                //找到右子树最小结点
                Node minNode = x.right;
                while (minNode.left != null) {
                    minNode = minNode.left;
                }
                //删除右子树中的最小结点
                Node n = x.right;
                while (n.left != null) {
                    if (n.left.left == null) {
                        if (n.left.right == null) {
                            n.left = null;
                        } else {
                            n.left = n.left.right;
                            break;
                        }
                    } else {
                        n = n.left;
                    }
                }
                //最小结点分别指向删除结点的左右子树
                minNode.left = x.left;
                minNode.right = x.right;
                //删除结点的父结点,指向最小结点
                x = minNode;
            }
        }
        return x;
    }

    public Key minKey() {
        return minKey(root);
    }

    public Key minKey(Node<Key, Value> x) {
        if (x == null) {
            return null;
        } else {
            if (x.left != null) {
                return minKey(x.left);
            } else {
                return x.getKey();
            }
        }
    }

    public Key maxKey() {
        return maxKey(root);
    }

    public Key maxKey(Node<Key, Value> x) {
        if (x == null) {
            return null;
        } else {
            if (x.right != null) {
                return maxKey(x.right);
            } else {
                return x.getKey();
            }
        }
    }

    public List<Key> preErgodic() {
        List<Key> keys = new ArrayList<Key>();
        this.preErgodic(root, keys);
        return keys;
    }

    public void preErgodic(Node<Key, Value> x, List<Key> keys) {
        if (x == null) {
            return;
        } else {
            keys.add(x.getKey());
            if (x.left != null) {
                preErgodic(x.left, keys);
            }
            if (x.right != null) {
                preErgodic(x.right, keys);
            }
        }
    }

    public List<Key> middleErgodic() {
        List<Key> keys = new ArrayList<Key>();
        this.middleErgodic(root, keys);
        return keys;
    }

    public void middleErgodic(Node<Key, Value> x, List<Key> keys) {
        if (x == null) {
            return;
        } else {
            if (x.left != null) {
                middleErgodic(x.left, keys);
            }
            keys.add(x.getKey());
            if (x.right != null) {
                middleErgodic(x.right, keys);
            }
        }
    }

    public List<Key> postErgodic() {
        List<Key> keys = new ArrayList<Key>();
        this.postErgodic(root, keys);
        return keys;
    }

    public void postErgodic(Node<Key, Value> x, List<Key> keys) {
        if (x == null) {
            return;
        } else {
            if (x.left != null) {
                postErgodic(x.left, keys);
            }
            if (x.right != null) {
                postErgodic(x.right, keys);
            }
            keys.add(x.getKey());
        }
    }

    public List<Key> layerErgodic() {
        List<Key> keys = new ArrayList<Key>();
        ArrayList<Node<Key, Value>> nodes = new ArrayList<Node<Key, Value>>();
        nodes.add(root);
        this.layerErgodic(nodes, keys);
        return keys;
    }

    public List<Key> layerErgodic1() {
        List<Key> keys = new ArrayList<Key>();
        List<Node<Key, Value>> nodes = new ArrayList<Node<Key, Value>>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            List<Node<Key, Value>> nextLayer = new ArrayList<Node<Key, Value>>();
            for (int i = 0; i < nodes.size(); i++) {
                Node<Key, Value> node = nodes.get(i);
                keys.add(node.getKey());
                if (node.left != null) {
                    nextLayer.add(node.left);
                }
                if (node.right != null) {
                    nextLayer.add(node.right);
                }
            }
            nodes = nextLayer;
        }
        return keys;
    }

    public void layerErgodic(List<Node<Key, Value>> xs, List<Key> keys) {
        if (xs == null || xs.isEmpty()) {
            return;
        } else {
            List<Node<Key, Value>> nextLayer = new ArrayList<Node<Key, Value>>();
            for (Node<Key, Value> x : xs) {
                keys.add(x.getKey());
                if (x.left != null) {
                    nextLayer.add(x.left);
                }
                if (x.right != null) {
                    nextLayer.add(x.right);
                }
            }
            layerErgodic(nextLayer, keys);
        }
    }

    /**
     * 树的最大深度
     *
     * @return
     */
    public int maxDepth() {
        return maxDepth(root);
    }

    /**
     * 指定结点的最大深度
     *
     * @param x
     * @return
     */
    public int maxDepth(Node x) {
        int maxDepth = 0;
        if (x == null) {
            return maxDepth;
        } else {
            List<Node<Key, Value>> nodes = new ArrayList<Node<Key, Value>>();
            nodes.add(x);
            while (!nodes.isEmpty()) {
                maxDepth++;
                List<Node<Key, Value>> nextLayer = new ArrayList<Node<Key, Value>>();
                for (int i = 0; i < nodes.size(); i++) {
                    Node<Key, Value> node = nodes.get(i);
                    if (node.left != null) {
                        nextLayer.add(node.left);
                    }
                    if (node.right != null) {
                        nextLayer.add(node.right);
                    }
                }
                nodes = nextLayer;
            }
        }
        return maxDepth;
    }

    /**
     * 树的最大深度
     *
     * @return
     */
    public int maxDepth1() {
        return maxDepth1(root);
    }

    /**
     * 指定结点的最大深度
     *
     * @param x
     * @return
     */
    public int maxDepth1(Node x) {
        int maxDepth = 0;
        if (x == null) {
            return maxDepth;
        } else {
            //左子树的最大深度
            int maxL = 0;
            if (x.left != null) {
                maxL = maxDepth1(x.left);
            }
            //右子树的最大深度
            int maxR = 0;
            if (x.right != null) {
                maxR = maxDepth1(x.right);
            }
            maxDepth = maxL > maxR ? (maxL + 1) : (maxR + 1);
        }
        return maxDepth;
    }
}