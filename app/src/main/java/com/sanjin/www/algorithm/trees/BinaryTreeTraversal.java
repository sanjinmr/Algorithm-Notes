package com.sanjin.www.algorithm.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class BinaryTreeTraversal {

    public static void test() {
        //BST bst = testInsert();

        //System.out.println("preOrder=============");
        //preOrder(root);

        //System.out.println("inOrder=============");
        //inOrder(root);

        //System.out.println("postOrder=============");
        //postOrder(root);

        BinaryTreeNode node1 = new BinaryTreeNode("1");
        BinaryTreeNode node2 = new BinaryTreeNode("2");
        BinaryTreeNode node3 = new BinaryTreeNode("3");
        BinaryTreeNode node4 = new BinaryTreeNode("4");
        BinaryTreeNode node5 = new BinaryTreeNode("5");
        BinaryTreeNode node6 = new BinaryTreeNode("6");
        BinaryTreeNode node7 = new BinaryTreeNode("7");

        node1.mLeft = node2;
        node1.mRight = node3;

        node2.mLeft = node4;
        node2.mRight = node5;

        node3.mLeft = node6;
        node3.mRight = node7;

        BinaryTreeTraversal test = new BinaryTreeTraversal();
        System.out.println("层序遍历：");
        test.layerOrder(node1);
        System.out.println();
        System.out.println("循环中序遍历：");
        test.inOrderLoop(node1);
        System.out.println("循环中序遍历简单版本：");
        test.inOrderLoopSimple(node1);
        System.out.println("循环后序遍历简单版本：");
        test.postOrderLoopSimple(node1);
        System.out.println("循环后序遍历反转版本：");
        test.postOrderLoopSimpleReverse(node1);
    }

    private BinaryTreeNode<String> root;

    private static class BinaryTreeNode<T> {
        T mValue;
        BinaryTreeNode<T> mLeft;
        BinaryTreeNode<T> mRight;

        public BinaryTreeNode(T value) {
            mValue = value;
        }
    }


    /**
     * 层序遍历
     * @param root
     */
    private void layerOrder(BinaryTreeNode<String> root) {
        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode<String>> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode<String> node = queue.poll();
            if (node != null) {
                System.out.print(node.mValue);
                System.out.print("、");
                if (node.mLeft != null) {
                    queue.offer(node.mLeft);
                }
                if (node.mRight != null) {
                    queue.offer(node.mRight);
                }
            }
        }
    }


    // -------------- S= 二叉树，递归遍历：前序、中序、后序 ---------------

    public void preOrder(BinaryTreeNode<String> root) {
        if (root == null) {
            return;
        }
        System.out.println("value: " + root.mValue);
        preOrder(root.mLeft);
        preOrder(root.mRight);
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inOrder(BinaryTreeNode<String> root) {
        if (root == null) {
            return;
        }
        inOrder(root.mLeft);
        System.out.println("value: " + root.mValue);
        inOrder(root.mRight);
    }

    public void postOrder(BinaryTreeNode<String> root) {
        if (root == null) {
            return;
        }
        postOrder(root.mLeft);
        postOrder(root.mRight);
        System.out.println("value: " + root.mValue);
    }

    // -------------- E= 二叉树，递归遍历：前序、中序、后序 ---------------


    // -------------- S= 二叉树，循环遍历：前序、中序、后序 ---------------

    /**
     * 循环前序遍历的简洁版
     * @param root
     */
    public void preOrderLoopSimple(BinaryTreeNode<String> root) {
        Deque<BinaryTreeNode<String>> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {// 将处理root节点和栈中父节点的逻辑合并在一个while中了
            while (root != null) {
                stack.push(root);
                System.out.println("value: " + root.mValue);
                root = root.mLeft;
            }

            BinaryTreeNode<String> leftOrParent = stack.pop();
            if (leftOrParent.mRight != null) {
                root = leftOrParent.mRight; // 如果有右节点，则以右节点为root进行循环
            } else {
                root = null; // 如果没有右节点，则从栈中取出父节点进行循环
            }
            // 上述代码可以简化为：root = stack.pop().right;
        }
    }

    /**
     * 循环中序遍历的简洁版
     * @param root
     */
    public void inOrderLoopSimple(BinaryTreeNode<String> root) {
        Deque<BinaryTreeNode<String>> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {// 将处理root节点和栈中父节点的逻辑合并在一个while中了
            while (root != null) {
                stack.push(root);
                root = root.mLeft;
            }

            BinaryTreeNode<String> leftOrParent = stack.pop();
            System.out.println("value： " + leftOrParent.mValue);

            if (leftOrParent.mRight != null) {
                root = leftOrParent.mRight; // 如果有右节点，则以右节点为root进行循环
            } else {
                root = null; // 如果没有右节点，则从栈中取出父节点进行循环
            }
            // 上述代码可以简化为：root = leftMostNode.right;
        }
    }

    /**
     * 循环后序遍历的简洁版 -- 反转法
     *
     * 先序是根—左—右，而后序是左-右-根，可以将先序改成根-右-左，然后将结果反转：左右根 - 根右左
     *
     * @param root
     */
    public void postOrderLoopSimpleReverse(BinaryTreeNode<String> root) {
        List<String> results = new ArrayList<>();

        Deque<BinaryTreeNode<String>> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) { // 将处理root节点和栈中父节点的逻辑合并在一个while中了
            // 如果root不为空：将root节点和右节点都入栈，并打印
            while (root != null) {
                stack.push(root);
                results.add(root.mValue);
                root = root.mRight;
            }

            // 判断最右节点或父节点是否有左节点，并做相应处理
            BinaryTreeNode<String> rightOrParent = stack.pop();
            if (rightOrParent.mLeft != null) {
                root = rightOrParent.mLeft; // 如果有左节点，则以左节点为root进行循环
            } else {
                root = null; // 如果没有左节点，则从栈中取出父节点进行循环
            }
            // 上述代码可简化为：root = stack.pop().mLeft;
        }

        // 反转并打印
        Collections.reverse(results);
        for (int i = 0; i < results.size(); i ++) {
            System.out.println("value: " + results.get(i));
        }
    }


    /**
     * 后序遍历
     *
     * @param root
     */
    public void postOrderLoopSimple(BinaryTreeNode<String> root) {
        BinaryTreeNode<String> prePrintedNode = null; // 缓存前一个打印的节点

        Deque<BinaryTreeNode<String>> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) { // 将处理root节点和栈中父节点的逻辑合并在一个while中了
            // 将节点root和其左节点都加入栈
            while (root != null) {
                stack.push(root);
                root = root.mLeft;
            }

            // 检查栈顶的节点是否需要出栈
            BinaryTreeNode<String> leftOrParent = stack.peek(); // 只查看,不一定要出栈
            if (leftOrParent == null) {
                return;
            }
            if (leftOrParent.mRight == null || leftOrParent.mRight == prePrintedNode) { // 如果没有右节点或右节点已经打印了，则该节点可以出栈
                System.out.println("value: " + leftOrParent.mValue);
                stack.pop();
                prePrintedNode = leftOrParent;
                root = null; // 让下次循环直接从栈中取
            } else { // 如果有右节点，且还没打印，则以右节点为root进行循环
                root = leftOrParent.mRight;
            }
        }
    }

    public void inOrderLoop(BinaryTreeNode<String> root) {
        Deque<BinaryTreeNode<String>> stack = new ArrayDeque<>(); // 用栈处理，ArrayDeque作为栈使

        while(root != null) {

            // 将root及其所有左节点放入栈中
            stack.push(root);
            BinaryTreeNode<String> left = root.mLeft;
            root = null;
            while(left != null) {
                stack.push(left);
                left = left.mLeft;
            }

            // 取出并打印最左节点
            BinaryTreeNode<String> leftMostNode = stack.pop();
            System.out.println("value: " + leftMostNode.mValue);

            // 打印最左节点的右节点或父节点
            if (leftMostNode.mRight != null) { // 如果还有右节点，需要处理右节点
                root = leftMostNode.mRight;
            } else { // 如果最左节点没有右节点，说明它是一个叶子结点，需要我们从栈中取出它的父节点（栈顶就是）
                while (!stack.isEmpty()) {
                    BinaryTreeNode<String> parent = stack.pop(); // 取出父节点并打印
                    System.out.println("value: " + parent.mValue);

                    if (parent.mRight != null) { // 如果某个父节点有右子节点，则结束打印父节点，去打印右节点
                        root = parent.mRight;
                        break;
                    } else {
                        // 如果父节点没有右子节点，则继续打印栈里的其他父节点，直到遇见根节点，如果根节点也没有右节点就结束所有循环
                    }
                }
            }
        }
    }

    // -------------- E= 二叉树，循环遍历：前序、中序、后序 ---------------
}
