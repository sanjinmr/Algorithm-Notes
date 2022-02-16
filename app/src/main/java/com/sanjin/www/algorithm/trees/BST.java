package com.sanjin.www.algorithm.trees;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/2/11
 * desc: 二叉搜索树
 * note:
 */
public class BST {

    private BinaryTreeNode<Integer> root;

    private static class BinaryTreeNode<T> {
        T mValue;
        BinaryTreeNode<T> mLeft;
        BinaryTreeNode<T> mRight;

        public BinaryTreeNode(T value) {
            mValue = value;
        }
    }

    public static void test() {
        BST bst = testInsert();

        System.out.println("preOrder=============");
        bst.preOrder(bst.root);

        System.out.println("inOrder=============");
        bst.inOrder(bst.root);

        System.out.println("postOrder=============");
        bst.postOrder(bst.root);
    }

    public static BST testInsert() {

        BST bst = new BST();

        bst.insert(40);
        bst.insert(20);
        bst.insert(60);
        bst.insert(50);
        bst.insert(70);
        bst.insert(30);
        bst.insert(10);

        return bst;
    }

    // -------------- S= 二叉搜索树，插入 ---------------

    /**
     * 二叉搜索树，新节点的插入
     * @param value
     */
    public void insert(int value) {
        if (root == null) {
            root = new BinaryTreeNode<>(value);
            return;
        }

        BinaryTreeNode<Integer> parent = null;
        BinaryTreeNode<Integer> current = root;

        while (true) {
            if (value < current.mValue) {
                parent = current;
                current = current.mLeft;
                if (current == null) {
                    parent.mLeft = new BinaryTreeNode<>(value);
                    return;
                }
            } else if (value > current.mValue) {
                parent = current;
                current = current.mRight;
                if (current == null) {
                    parent.mRight = new BinaryTreeNode<>(value);
                    return;
                }
            }
        }
    }

    // -------------- E= 二叉搜索树，插入 ---------------


    // -------------- S= 二叉搜索树，删除 ---------------

    /**
     * 二叉搜索树，根据value删除对应的节点
     * @param value
     * @return
     */
    public boolean delete(int value) { // 删除节点
        BinaryTreeNode<Integer> current = root;
        BinaryTreeNode<Integer> parent = null;

        // 先探寻要删除的节点
        boolean isRightChild = true;
        while (current.mValue != value) {
            parent = current;
            if (value > current.mValue) {
                current = current.mRight;
                isRightChild = true;
            } else {
                current = current.mLeft;
                isRightChild = false;
            }
            if (current == null) {
                return false; // 没有找到要删除的节点
            }
        }

        // 此时的current就是要删除的节点。parent为其父节点

        // 要删除的节点为叶子节点
        if (current.mLeft == null && current.mRight == null) {
            if (current == root) {
                // 如果删除的是根节点，且根节点为叶子节点，则整棵树清空
                root = null;
            } else {
                if (isRightChild) {
                    parent.mRight = null;
                } else {
                    parent.mLeft = null;
                }
            }
            return true;
        }

        // 要删除的节点有一个子节点
        // 1. 要删除的节点只有一个右节点
        else if (current.mLeft == null) {
            if (current == root) {
                root = current.mRight;
            } else if (isRightChild) {
                parent.mRight = current.mRight;
            } else {
                parent.mLeft = current.mRight;
            }

            return true;
        }
        // 2. 要删除的节点只有一个左节点
        else if (current.mRight == null) {
            if (current == root) {
                root = current.mLeft;
            } else if (isRightChild) {
                parent.mRight = current.mLeft;
            } else {
                parent.mLeft = current.mLeft;
            }

            return true;
        }

        // 要删除的节点有两个子节点（左节点和右节点都有）
        else {
            // 找到要删除节点的后继节点
            BinaryTreeNode<Integer> successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isRightChild) {
                parent.mRight = successor;
            } else {
                parent.mLeft = successor;
            }

            successor.mLeft = current.mLeft;
            return true;
        }
    }

    // 寻找要删除节点的中序后继节点
    // delNode待删除节点
    private BinaryTreeNode<Integer> getSuccessor(BinaryTreeNode<Integer> delNode) {

        // successorParent为后继节点的父节点，默认待删除节点为后继节点的父节点
        BinaryTreeNode<Integer> successorParent = delNode;

        // successor为待删除节点的后继节点， 默认为delNode.mRight
        BinaryTreeNode<Integer> successor = delNode.mRight;

        // 如果后继节点有左子树，则在左子树中寻找真正的后继节点
        // 如果没有，则delNode的右节点为后继节点，跳过这个while循环
        while (successor.mLeft != null) {
            successorParent = successor;
            successor = successor.mLeft;
        }

        // 如果后继结点为要删除结点的右子树的左子，需要预先调整一下要删除结点的右子树
        // 1. 把后继节点的右子节点作为后继节点父节点的左子节点.
        // 2. 把要删除节点的右子节点作为后继节点的右节点.
        if (successor != delNode.mRight) { // 后继不为delNode的右子即说明后继在右子树的左树上
            successorParent.mLeft = successor.mRight;
            successor.mRight = delNode.mRight;
        }

        return successor;
    }

    // -------------- E= 二叉搜索树，删除 ---------------


    // -------------- S= 二叉搜索树，查找 ---------------

    public BinaryTreeNode<Integer> find(int value) {
        BinaryTreeNode<Integer> current = root;
        while (value != current.mValue) {
            if (value < current.mValue) {
                current = current.mLeft;
            } else {
                current = current.mRight;
            }
            // 如果遍历到叶子节点（它的左右子节点为null）都没有找到，则说明树中没有要查找的节点
            if (current == null) {
                return null;
            }
        }

        return current;
    }

    // -------------- E= 二叉搜索树，查找 ---------------


    // -------------- S= 二叉搜索树，遍历：前序、中序、后序 ---------------

    public void preOrder(BinaryTreeNode<Integer> root) {
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
    public void inOrder(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        inOrder(root.mLeft);
        System.out.println("value: " + root.mValue);
        inOrder(root.mRight);
    }

    public void postOrder(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        postOrder(root.mLeft);
        postOrder(root.mRight);
        System.out.println("value: " + root.mValue);
    }

    // -------------- E= 二叉搜索树，遍历：前序、中序、后序 ---------------

}
