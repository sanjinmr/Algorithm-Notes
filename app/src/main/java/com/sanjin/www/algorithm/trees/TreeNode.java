package com.sanjin.www.algorithm.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/2/7
 * desc:
 * note:
 */
class TreeNode<T> {
    T value;
    // 普通树的子节点，放在 一个队列中（一般是双端队列，Java中可以使用LinkedList）。
    List<TreeNode<T>> children;

    // 普通树的父节点。
    // 该节点一般不用使用，中在少数部分算法问题中需要使用mParent，如面试官指定可以使用mParent来解决问题时。
    TreeNode<T> mParent;

    public TreeNode(T value) {
        this.value = value;
        children = new ArrayList<>();
    }
}
