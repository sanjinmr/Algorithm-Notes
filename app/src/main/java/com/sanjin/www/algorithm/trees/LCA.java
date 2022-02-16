package com.sanjin.www.algorithm.trees;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/2/14
 * desc: 最低公共祖先
 * note:
 */
public class LCA {

    // --------- S= 工具方法 ----------------------------

    private BinaryTreeNode CreateBinaryTreeNode(int i) {
        return new BinaryTreeNode(i);
    }

    private void ConnectTreeNodes(TreeNode pNode1, TreeNode pNode2) {
        pNode1.children.add(pNode2);
    }

    private TreeNode CreateTreeNode(int i) {
        return new TreeNode(i);
    }

    // --------- E= 工具方法 ----------------------------


    // ------------- 自己不可以是自己祖先节点 ----------------------------

    // 形状普通的树  -- 递归
    //              1
    //            /   \
    //           2     3
    //       /       \
    //      4         5
    //     / \      / |  \
    //    6   7    8  9  10
    public void test() {
        TreeNode pNode1 = CreateTreeNode(1);
        TreeNode pNode2 = CreateTreeNode(2);
        TreeNode pNode3 = CreateTreeNode(3);
        TreeNode pNode4 = CreateTreeNode(4);
        TreeNode pNode5 = CreateTreeNode(5);
        TreeNode pNode6 = CreateTreeNode(6);
        TreeNode pNode7 = CreateTreeNode(7);
        TreeNode pNode8 = CreateTreeNode(8);
        TreeNode pNode9 = CreateTreeNode(9);
        TreeNode pNode10 = CreateTreeNode(10);

        ConnectTreeNodes(pNode1, pNode2);
        ConnectTreeNodes(pNode1, pNode3);

        ConnectTreeNodes(pNode2, pNode4);
        ConnectTreeNodes(pNode2, pNode5);

        ConnectTreeNodes(pNode4, pNode6);
        ConnectTreeNodes(pNode4, pNode7);

        ConnectTreeNodes(pNode5, pNode8);
        ConnectTreeNodes(pNode5, pNode9);
        ConnectTreeNodes(pNode5, pNode10);

        // 当交点为自己时，最低公共祖先为交点的parent。
        // 当交点不为两个节点中任意一个时，最低公共祖先为交点。
        // 当根节点为交点时，最低公共祖先为NULL
        TreeNode node = getLastCommonParent(pNode1, pNode1, pNode2);
        if (null != node) {
            System.out.println("result: " + node.value);
        } else {
            System.out.println("result == NULL ");
        }

    }

    public <T> TreeNode<T> getLastCommonParent(TreeNode<T> root, TreeNode<T> node1, TreeNode<T> node2) {
        if (root == null || node1 == null || node2 == null) {
            return null;
        }
        LinkedList<TreeNode<T>> path1 = new LinkedList<>();
        getNodePath(root, node1, path1);
        LinkedList<TreeNode<T>> path2 = new LinkedList<>();
        getNodePath(root, node2, path2);
        return getLastCommonNode(path1, path2);
    }

    private <T> boolean getNodePath(TreeNode<T> root, TreeNode<T> node, LinkedList<TreeNode<T>> path) {
        System.out.println("getNodePath root: "+ root.value + " node: "+ node.value);
        if (root == node) {
            return true;
        }
        path.add(root);
        boolean found = false;
        for (int i = 0; !found && i < root.children.size(); i++) {
            found = getNodePath(root.children.get(i), node, path);
        }
        if (!found) {
            path.removeLast();
        }
        return found;
    }

    private <T> TreeNode<T> getLastCommonNode(LinkedList<TreeNode<T>> path1, LinkedList<TreeNode<T>> path2) {

        for (TreeNode node : path1) {
            System.out.println("getLastCommonNode result1: " + node.value);
        }
        for (TreeNode node : path2) {
            System.out.println("getLastCommonNode result2: " + node.value);
        }

        Iterator<TreeNode<T>> iterator1 = path1.iterator();
        Iterator<TreeNode<T>> iterator2 = path2.iterator();

        TreeNode<T> last = null;
        while (iterator1.hasNext() && iterator2.hasNext()) {
            TreeNode<T> node = iterator1.next();
            if (node == iterator2.next()) {
                last = node;
            } else {
                break;
            }
        }
        return last;
    }



    // 二叉树 -- 递归
    //              1
    //            /   \
    //           2     3
    //       /       \
    //      4         5
    //     / \      /   \
    //    6   7    8     9
    public void testBinaryTree1() {
        BinaryTreeNode node1 = CreateBinaryTreeNode(1);
        BinaryTreeNode node2 = CreateBinaryTreeNode(2);
        BinaryTreeNode node3 = CreateBinaryTreeNode(3);
        BinaryTreeNode node4 = CreateBinaryTreeNode(4);
        BinaryTreeNode node5 = CreateBinaryTreeNode(5);
        BinaryTreeNode node6 = CreateBinaryTreeNode(6);
        BinaryTreeNode node7 = CreateBinaryTreeNode(7);
        BinaryTreeNode node8 = CreateBinaryTreeNode(8);
        BinaryTreeNode node9 = CreateBinaryTreeNode(9);

        node1.mLeft = node2;
        node1.mRight = node3;
        node2.mLeft = node4;
        node2.mRight = node5;
        node4.mLeft = node6;
        node4.mRight = node7;
        node5.mLeft = node8;
        node5.mRight = node9;

        BinaryTreeNode node = lowestCommonAncestorBinaryTree1(node1, node2, node9);
        if (null == node) {
            System.out.println("result: " + null);
        } else {
            System.out.println("result: " + node.mValue);
        }
    }

    /**
     * 二叉树中查找两个节点的最近公共祖先（递归）
     * 注：自己不可以是自己祖先节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public BinaryTreeNode lowestCommonAncestorBinaryTree1
    (BinaryTreeNode root, BinaryTreeNode p, BinaryTreeNode q) {

        if (root == null || root == p || root == q) {
            return null;
        }

        /*if (root.mLeft == p || root.mLeft == q) {
            return root;
        }

        if (root.mRight == q || root.mRight == p) {
            return root;
        }*/

        // 轮询的条件中，root不为p或q
        return lowestCommonAncestorBinaryTree1Inter(root, p, q);
    }

    boolean hasFound;

    public BinaryTreeNode lowestCommonAncestorBinaryTree1Inter
            (BinaryTreeNode root, BinaryTreeNode p, BinaryTreeNode q) {

        if (root == null) {
            System.out.println("execute function. root: " + null);
            return null;
        } else {
            System.out.println("execute function. root: " + root.mValue);
        }

        // 从上往下执行（队列式递归）（从上往下执行，可以避免重复计算）
        // 实际上，这个逻辑是最后一次递归才执行，因为一旦遇到这种情况就会结束递归
        // 即一旦递归到一个等于p或q的节点，就结束递归
        // 如果该节点等于p或q节点，直接返回该节点
        if (root == p || root == q) {
            System.out.println("Has found a p or q. root: " + root.mValue);
            return root;
        }

        System.out.println("prepare check. left of " + root.mValue);
        // 递归检查left子树是否包含p或q节点
        BinaryTreeNode left = lowestCommonAncestorBinaryTree1Inter(root.mLeft, p, q);
        System.out.println("prepare check. right of " + root.mValue);
        // 递归检查right子树是否包含p或q节点
        BinaryTreeNode right = lowestCommonAncestorBinaryTree1Inter(root.mRight, p, q);

        // 从下往上执行（栈式递归）
        if (left != null && right != null) {
            System.out.println("Has left and right root: " + root.mValue + " left: " + left.mValue + " right: " + right.mValue);
            // 发现交点，且交点不为自己时，直接返回root
            return root;
        } else if (left != null) {
            System.out.println("Has found left only root: " + root.mValue + " left: " + left.mValue + " right: " + null);
            // 如果是第一次发现左子树有目标节点但右子树没有（用于兼容交点为自己时），则返回该节点。否则返回left
            if (!hasFound) {
                hasFound = true;
                return root;
            } else {
                return left;
            }
        } else if (right != null) {
            System.out.println("Has found right only root: " + root.mValue + " left: " + null + " right: " + right.mValue);
            // 如果是第一次发现右子树有目标节点但左子树没有（用于兼容交点为自己时），则返回该节点。否则返回right
            if (!hasFound) {
                hasFound = true;
                return root;
            } else {
                return right;
            }
        } else {
            return null;
        }
    }





    // ------------- 自己可以是自己祖先节点 ----------------------------

    // 二叉树 -- 递归
    //              1
    //            /   \
    //           2     3
    //       /       \
    //      4         5
    //     / \      /   \
    //    6   7    8     9
    public void testBinaryTree() {
        BinaryTreeNode node1 = CreateBinaryTreeNode(1);
        BinaryTreeNode node2 = CreateBinaryTreeNode(2);
        BinaryTreeNode node3 = CreateBinaryTreeNode(3);
        BinaryTreeNode node4 = CreateBinaryTreeNode(4);
        BinaryTreeNode node5 = CreateBinaryTreeNode(5);
        BinaryTreeNode node6 = CreateBinaryTreeNode(6);
        BinaryTreeNode node7 = CreateBinaryTreeNode(7);
        BinaryTreeNode node8 = CreateBinaryTreeNode(8);
        BinaryTreeNode node9 = CreateBinaryTreeNode(9);

        node1.mLeft = node2;
        node1.mRight = node3;
        node2.mLeft = node4;
        node2.mRight = node5;
        node4.mLeft = node6;
        node4.mRight = node7;
        node5.mLeft = node8;
        node5.mRight = node9;

        BinaryTreeNode node = lowestCommonAncestorBinaryTree(node1, node2, node6);
        System.out.println("result: " + node.mValue);

    }

    /**
     * 二叉树中查找两个节点的最近公共祖先（递归）
     * 注：自己可以是自己祖先节点
     * 不足：即使最低公共祖先在左子树，在遍历完左子树后，也会去遍历右子树（这一段遍历就是浪费了）
     * 优化：1.可以在判断找到最低公共祖先时，设置一个标识，让遍历其他节点的行为结束或不要执行。
     * 但是，只有在遍历到目标节点在左子树和右子树时，才能确定找到了最低公共祖先。
     * 如果两个目标节点处于同一子树，还是需要遍历另一子树，得知其没有目标节点才能确定最低公共祖先。

     注意p,q必然存在树内, 且所有节点的值唯一!!!
     递归思想, 对以root为根的(子)树进行查找p和q, 如果root == null || p || q 直接返回root
     表示对于当前树的查找已经完毕, 否则对左右子树进行查找, 根据左右子树的返回值判断:
     1. 左右子树的返回值都不为null, 由于值唯一左右子树的返回值就是p和q, 此时root为LCA
     2. 如果左右子树返回值只有一个不为null, 说明只有p和q存在与左或右子树中, 最先找到的那个节点为LCA
     3. 左右子树返回值均为null, p和q均不在树中, 返回null
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public BinaryTreeNode lowestCommonAncestorBinaryTree
    (BinaryTreeNode root, BinaryTreeNode p, BinaryTreeNode q) {
        if (root == null) {
            System.out.println("execute function. root: " + null);
            return null;
        } else {
            System.out.println("execute function. root: " + root.mValue);
        }

        // 一旦遇见等于p或q的节点，就返回，不再继续探测该节点下面的其他节点了。
        // 如果p是root，q在p的子树中，这里也不用再管了。因为p和q的公共祖先最少都应该在p。
        if (root == p || root == q) {
            System.out.println("Has found a p or q. root: " + root.mValue);
            return root;
        }

        System.out.println("prepare check. left of " + root.mValue);
        BinaryTreeNode left = lowestCommonAncestorBinaryTree(root.mLeft, p, q);
        System.out.println("prepare check. right of " + root.mValue);
        BinaryTreeNode right = lowestCommonAncestorBinaryTree(root.mRight, p, q);
        if (left != null && right != null) {
            System.out.println("Has left and right root: " + root.mValue + " left: " + left.mValue + " right: " + right.mValue);
            return root;
        } else if (left != null) {
            System.out.println("Has found left only root: " + root.mValue + " left: " + left.mValue + " right: " + null);
            return left;
        } else if (right != null) {
            System.out.println("Has found right only root: " + root.mValue + " left: " + null + " right: " + right.mValue);
            return right;
        } else {
            return null;
        }
    }


    // ------------ S= 二叉搜素树 ------------------------------------

    // 二叉树
    //           4
    //       /      \
    //      2        6
    //     / \      /  \
    //    1   3    5     7
    public void testBinarySearchTree() {
        BinaryTreeNode node1 = CreateBinaryTreeNode(1);
        BinaryTreeNode node2 = CreateBinaryTreeNode(2);
        BinaryTreeNode node3 = CreateBinaryTreeNode(3);
        BinaryTreeNode node4 = CreateBinaryTreeNode(4);
        BinaryTreeNode node5 = CreateBinaryTreeNode(5);
        BinaryTreeNode node6 = CreateBinaryTreeNode(6);
        BinaryTreeNode node7 = CreateBinaryTreeNode(7);

        node4.mLeft = node2;
        node4.mRight = node6;
        node2.mLeft = node1;
        node2.mRight = node3;
        node6.mLeft = node5;
        node6.mRight = node7;

        BinaryTreeNode node = lowestAncestorBSTTranversal(node4, node1, node7);
        if (null == node) {
            System.out.println("result: " + null);
        } else {
            System.out.println("result: " + node.mValue);
        }
    }

    /**
     * 查找二叉搜索树的最近公共祖先 -- 循环
     * @param root
     * @param p
     * @param q
     * @return
     */
    public BinaryTreeNode<Integer> lowestAncestorBSTTranversal(
            BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> q) {
        if (root == null) {
            return null;
        }

        BinaryTreeNode<Integer> min = minNode(p, q);
        BinaryTreeNode<Integer> max = maxNode(p,q);

        while (root != null && !(root.mValue >= min.mValue && root.mValue <= max.mValue)) {
            if (root.mValue > max.mValue) {
                root = root.mLeft;
            } else if (root.mValue < min.mValue) {
                root = root.mRight;
            }
        }

        return root;
    }

    /**
     * 查找二叉搜索树的最近公共祖先 -- 递归
     * @param root
     * @param p
     * @param q
     * @return
     */
    public BinaryTreeNode<Integer> lowestAncestorBinarySearchTree(
            BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> q) {
        if (root == null) {
            return null;
        }

        BinaryTreeNode<Integer> min = minNode(p, q);
        BinaryTreeNode<Integer> max = maxNode(p, q);

        if (root.mValue >= min.mValue && root.mValue <= max.mValue) {
            return root;
        } else if (root.mValue > max.mValue) {
            return lowestAncestorBinarySearchTree(root.mLeft, p, q);
        } else {
            return lowestAncestorBinarySearchTree(root.mRight, p, q);
        }
    }

    private BinaryTreeNode<Integer> minNode(BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> q) {
        if (p.mValue < q.mValue) {
            return p;
        }
        return q;
    }

    private BinaryTreeNode<Integer> maxNode(BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> q) {
        if (p.mValue > q.mValue) {
            return p;
        }
        return q;
    }

    // ------------ E= 二叉搜素树 ------------------------------------
}
