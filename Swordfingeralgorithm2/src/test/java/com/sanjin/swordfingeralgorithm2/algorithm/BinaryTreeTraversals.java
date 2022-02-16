package com.sanjin.swordfingeralgorithm2.algorithm;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/1/16
 * desc:
 * note:
 */
public class BinaryTreeTraversals {


    public void test() {

    }

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
    public BinaryTreeNode createBinaryTree(int[] preOrderValues, int[] midOrderValues, int length,
                                                              int preOrderStartIndex, int preOrderEndIndex,
                                                              int midOrderStartIndex, int midOrderEndIndex) throws Exception {

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
                    rootNode.pLeft = createBinaryTree(preOrderValues, midOrderValues, leftChildLength,
                            preLeftStartIndex, preLeftEndIndex,
                            midLeftStartIndex, midLeftEndIndex);
                }

                if (rightChildLength != 0) {
                    rootNode.pRight = createBinaryTree(preOrderValues, midOrderValues, rightChildLength,
                            preRightStartIndex, preRightEndIndex,
                            midRightStartIndex, midRightEndIndex);
                }
            }
        }

        return rootNode;
    }

    Queue<BinaryTreeNode> queue = new ArrayBlockingQueue<BinaryTreeNode>(8);

    @Test
    public void testBFS() {
        int[] preOrderValues = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] midOrderValues = {4, 7, 2, 1, 5, 3, 8, 6};

        try {
            BinaryTreeNode rootNode = createBinaryTree(preOrderValues, midOrderValues, 8,
                    0, 7, 0, 7);
            queue.add(rootNode);
            bfsTraversal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 广度优先遍历二叉树
     */
    public void bfsTraversal() {
        BinaryTreeNode node = queue.poll();
        if (node != null) {
            printNode(node.mValue);
            if (node.pLeft != null) {
                queue.offer(node.pLeft);
            }
            if (node.pRight != null) {
                queue.offer(node.pRight);
            }
            bfsTraversal();
        }
    }

    /**
     * 按层遍历二叉树 -- 循环
     * @param root
     */
    public void bfsTraversalLoop(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        queue.offer(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            if (node != null) {
                printNode(node.mValue);
                if (node.pLeft != null) {
                    queue.offer(node.pLeft);
                }
                if (node.pRight != null) {
                    queue.offer(node.pRight);
                }
            }
        }
    }

    /**
     * 前序遍历--递归实现
     * @param node
     */
    private static void preTraversal(BinaryTreeNode node) {
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
    private static void midTranversal(BinaryTreeNode node) {
        if (node != null) {
            midTranversal(node.pLeft);
            System.out.println("Node : " + node.mValue);
            midTranversal(node.pRight);
        }
    }

    /**
     * 后序遍历--递归实现
     * @param node
     */
    private static void postTranversal(BinaryTreeNode node) {
        if (node != null) {
            postTranversal(node.pLeft);
            postTranversal(node.pRight);
            System.out.println("Node : " + node.mValue);
        }
    }


    // 遍历二叉树
    @Deprecated
    private static void traversalTrees(BinaryTreeNode rootNode) {
        printNode(rootNode.mValue);
        if (rootNode.pLeft != null) {
            printLeft(rootNode.pLeft.mValue);
        } else {
            printLeft(null);
        }
        if (rootNode.pRight != null) {
            printRight(rootNode.pRight.mValue);
        } else {
            printRight(null);
        }

        if (rootNode.pLeft != null) {
            traversalTrees(rootNode.pLeft);
        }
        if (rootNode.pRight != null) {
            traversalTrees(rootNode.pRight);
        }
    }

    private static void printNode(Integer value) {
        System.out.println("Node : " + value);
    }
    private static void printLeft(Integer value) {
        System.out.println("Node-left : " + value);
    }
    private static void printRight(Integer value) {
        System.out.println("Node-right : " + value);
    }
}
