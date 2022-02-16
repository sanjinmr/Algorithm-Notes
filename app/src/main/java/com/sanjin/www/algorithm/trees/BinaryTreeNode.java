package com.sanjin.www.algorithm.trees;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/2/8
 * desc:
 * note:
 */
public class BinaryTreeNode<T> {

    public T mValue;
    public BinaryTreeNode<T> mLeft;
    public BinaryTreeNode<T> mRight;

    // 只有在部分情况下在会给二叉树的节点定义父节点
    // 比如：求最低公共祖先时
    public BinaryTreeNode<T> mPrent;

    public BinaryTreeNode(T value) {
        this.mValue = value;
    }
}
