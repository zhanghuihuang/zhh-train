package com.zhh.train.algorithm.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
    public class Node {
        Key key;
        Value value;
        Node left;
        Node right;
    }

    /**
     * 向树中插入一个键值对
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    /**
     * 在子树node插入一个键值对,并返回添加后的新树
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    public Node put(Node node, Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("键不能为空");
        }
        if (node == null) {
            //如果树为空,则返回一个结点的树
            size++;
            return new Node(key, value, null, null);
        } else {
            //比较key和当前结点的key大小
            int cmp = key.compareTo(node.key);
            if (cmp > 0) {
                //如果大于0,表示插入的键比当前结点大,往右子结点继续找,并让我的右子结点指向返回值
                node.right = put(node.right, key, value);
            } else if (cmp < 0) {
                //如果下于0,表示插入的键比当前结点小,往左子结点继续找,并让我的左子结点指向返回值
                node.left = put(node.left, key, value);
            } else {
                //如果等于,则替换值
                node.value = value;
            }
        }
        return node;
    }

    /**
     * 根据key查找树种对应的value
     *
     * @param key
     * @return
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 查找指定树node中的key对应的value
     *
     * @param node
     * @param key
     * @return
     */
    public Value get(Node node, Key key) {
        if (node == null || key == null) {
            return null;
        } else {
            int cmp = key.compareTo(node.key);
            if (cmp > 0) {
                //如果大于0,表示插入的键比当前结点大,往右子结点继续找,直到找到为止
                return get(node.right, key);
            } else if (cmp < 0) {
                //如果下于0,表示插入的键比当前结点小,往左子结点继续找,直到找到为止
                return get(node.left, key);
            } else {
                //如果等于,就是找到返回
                return node.value;
            }
        }
    }

    /**
     * 删除树中对应的键值对
     * 1.根据key找到这个删除结点
     * 2.找到删除结点的右子树中的最小结点顶替他的位置
     * 3.如果没有右子树,则左子结点直接顶上
     *
     * @param key
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    /**
     * 删除指定树node中对应的键值对,并返回删除后的新树
     *
     * @param node
     * @param key
     * @return
     */
    public Node delete(Node node, Key key) {
        if (node == null || key == null) {
            return node;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            //如果大于0,表示插入的键比当前结点大,往右子结点继续找,直到找到为止
            node.right = delete(node.right, key);
        } else if (cmp < 0) {
            //如果下于0,表示插入的键比当前结点小,往左子结点继续找,直到找到为止
            node.left = delete(node.left, key);
        } else {
            size--;
            //如果等于,就是找到,真正的删除操作
            if (node.right == null) {
                //如果右子树为空,则让左子结点顶替
                return node.left;
            }
            if (node.left == null) {
                //如果左子树为空,则让右子结点顶替
                return node.right;
            }
            //如果左右子树都不为空,则找到右子树的最小结点
            Node min = node.right;
            while (min.left != null) {
                if (min.left.left == null) {
                    if (min.left.right == null) {
                        min = min.left;
                    } else {
                        min.left = min.left.right;
                    }
                    break;
                } else {
                    min = min.left;
                }
            }
            //让右子树的最小结点顶替删除结点
            min.left = node.left;
            min.right = node.right;
            node = min;
        }
        return node;
    }

    /**
     * 获取树中的元素个数
     *
     * @return
     */
    public int size() {
        return size;
    }

    public Key minKey() {
        return minKey(root);
    }

    public Key minKey(Node x) {
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

    public Key maxKey(Node x) {
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

    public void preErgodic(Node x, List<Key> keys) {
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

    public void middleErgodic(Node x, List<Key> keys) {
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

    public void postErgodic(Node x, List<Key> keys) {
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
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(root);
        this.layerErgodic(nodes, keys);
        return keys;
    }

    public List<Key> layerErgodic1() {
        List<Key> keys = new ArrayList<Key>();
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            List<Node> nextLayer = new ArrayList<Node>();
            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
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

    public void layerErgodic(List<Node> xs, List<Key> keys) {
        if (xs == null || xs.isEmpty()) {
            return;
        } else {
            List<Node> nextLayer = new ArrayList<Node>();
            for (Node x : xs) {
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
            List<Node> nodes = new ArrayList<Node>();
            nodes.add(x);
            while (!nodes.isEmpty()) {
                maxDepth++;
                List<Node> nextLayer = new ArrayList<Node>();
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
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
