package com.sanjin.swordfingeralgorithm2.algorithm;

import org.junit.Test;

/**
 * 根据前序序列和中序序列重建二叉树
 */
public class BinaryTreeReBuild {

    class BinaryTreeNode {
        int mValue;
        BinaryTreeNode pLeft;
        BinaryTreeNode pRight;
        public BinaryTreeNode() {}
        public BinaryTreeNode(int value) {
            mValue = value;
        }
    }

    // 根据前序和中序序列创建二叉树
    public BinaryTreeNode reBuildBinaryTree(int[] preOrderValues, int[] midOrderValues, int length,
                                            int preOrderStartIndex, int preOrderEndIndex,
                                            int midOrderStartIndex, int midOrderEndIndex) throws Exception {
        System.out.println("reBuildBinaryTree length: " + length +
                " preOrderStartIndex: " + preOrderStartIndex +
                " preOrderEndIndex: " + preOrderEndIndex +
                " midOrderStartIndex: " + midOrderStartIndex +
                " midOrderEndIndex: " + midOrderEndIndex
        );

        if (preOrderValues.length != midOrderValues.length) {
            throw new Exception("Datas is invalid!");
        }

        // 1. 定义根节点
        BinaryTreeNode rootNode = new BinaryTreeNode();
        rootNode.mValue = preOrderValues[preOrderStartIndex];

        if (preOrderStartIndex == preOrderEndIndex) {
            rootNode.pLeft = null;
            rootNode.pRight = null;
            return rootNode;
        }

        // 2. 计算左子树的个数和右子树的个数，并定义根节点的左子树根节点，和根节点的右子树根节点
        for (int i = midOrderStartIndex; i <= midOrderEndIndex; i ++) {
            if (midOrderValues[i] == rootNode.mValue) {
                int leftChildLength = i - midOrderStartIndex;
                int rightChildLength = midOrderEndIndex - i;

                int preLeftStartIndex = preOrderStartIndex + 1;
                int preLeftEndIndex = preOrderStartIndex + leftChildLength;
                int preRightStartIndex = preLeftEndIndex + 1;
                int preRightEndIndex = preOrderEndIndex;

                int midLeftStartIndex = midOrderStartIndex;
                int midLeftEndIndex = i - 1;
                int midRightStartIndex = i + 1;
                int midRightEndIndex = midOrderEndIndex;

                if (leftChildLength != 0) {
                    rootNode.pLeft = reBuildBinaryTree(preOrderValues, midOrderValues, leftChildLength,
                            preLeftStartIndex, preLeftEndIndex,
                            midLeftStartIndex, midLeftEndIndex);
                }

                if (rightChildLength != 0) {
                    rootNode.pRight = reBuildBinaryTree(preOrderValues, midOrderValues, rightChildLength,
                            preRightStartIndex, preRightEndIndex,
                            midRightStartIndex, midRightEndIndex);
                }
            }
        }

        return rootNode;
    }


    @Test
    public void test() {
        int[] preOrderValues = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] midOrderValues = {4, 7, 2, 1, 5, 3, 8, 6};

        try {
            BinaryTreeNode rootNode = reBuildBinaryTree(preOrderValues, midOrderValues, 8,
                    0, 7, 0, 7);

            preTraversal(rootNode);
        } catch (Exception e) {
            e.printStackTrace();
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
}


















