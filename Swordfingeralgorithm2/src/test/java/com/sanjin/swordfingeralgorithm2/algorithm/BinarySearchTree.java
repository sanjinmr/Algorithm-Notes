package com.sanjin.swordfingeralgorithm2.algorithm;

import org.junit.Test;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/1/16
 * desc:
 * note:
 */
public class BinarySearchTree {

    class BinaryTreeNode {
        int mValue;
        BinaryTreeNode pLeft;
        BinaryTreeNode pRight;
        public BinaryTreeNode(int value) {
            mValue = value;
        }
    }


    @Test
    public void test() {
        int[] values = {1, 2, 4, 7, 3, 5, 6, 8};
        BinaryTreeNode pRoot = null;
        for (int newValue : values) {
            pRoot = buildBinarySearchTree(pRoot, newValue);
        }

        preTraversal(pRoot);
    }

    private BinaryTreeNode buildBinarySearchTree(BinaryTreeNode pRoot, int newValue) {
        if (pRoot == null) {
            pRoot = new BinaryTreeNode(newValue);
        } else {
            buildBinarySearchTreeCore(pRoot, newValue);
        }
        return pRoot;
    }

    /**
     * 实现二叉搜索树--递归实现
     * @param pRoot
     * @param newValue
     */
    private void buildBinarySearchTreeCore(BinaryTreeNode pRoot, int newValue) {
        if (newValue > pRoot.mValue) {
            if (pRoot.pRight == null) {
                pRoot.pRight = new BinaryTreeNode(newValue);
            } else {
                // 在右子树中找插入位置
                buildBinarySearchTreeCore(pRoot.pRight, newValue);
            }
        } else {
            if (pRoot.pLeft == null) {
                pRoot.pLeft = new BinaryTreeNode(newValue);
            } else {
                // 在左子树中找插入位置
                buildBinarySearchTreeCore(pRoot.pLeft, newValue);
            }
        }
    }

    /**
     * 前序遍历--递归实现
     * @param node
     */
    private void preTraversal(BinaryTreeNode node) {
        if (node != null) {
            System.out.println("Node : " + node.mValue);
            preTraversal(node.pLeft);
            preTraversal(node.pRight);
        }
    }

    /**
     * 中序遍历--递归实现
     * @param node
     */
    private void midTranversal(BinaryTreeNode node) {
        if (node != null) {
            midTranversal(node.pLeft);
            System.out.println("Node : " + node.mValue);
            midTranversal(node.pRight);
        }
    }
}
