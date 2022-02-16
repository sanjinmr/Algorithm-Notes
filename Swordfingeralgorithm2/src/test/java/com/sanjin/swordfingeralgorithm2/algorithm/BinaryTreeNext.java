package com.sanjin.swordfingeralgorithm2.algorithm;

import org.junit.Test;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/1/16
 * desc:
 * note:
 */
public class BinaryTreeNext {
    static class BinaryTreeNode {
        char mValue;
        BinaryTreeNode pLeft;
        BinaryTreeNode pRight;
        BinaryTreeNode pParent;
        public BinaryTreeNode() {}
        public BinaryTreeNode(char value) {
            mValue = value;
        }
    }

    /**
     * 查询在二叉树“中序排列”中某个节点的下一个节点的思路：
     * 1. 因为是中序排列，它的顺序大致如下：
     * B的左子树{A的左子树（左子树A、父节点、右子树）、父节点A、A的右子树（左子树、父节点、右子树）}、父节点B、B的右子树{略}
     * 简化如下：左子树、父节点、右子树
     * 可见，某个节点的下一个节点，只可能在父节点（或整个父节点枝干上）或右子树中。
     *
     * 2. 如果某个节点的身份是父节点，那么它的下一个节点一定在右子树中。
     * 所以，中序排列寻找下一个节点的突破口是，判断本节点的右子树。
     * 1）如果有右子树，下一个节点就一定在右子树中
     * 2）如果没有右子树，就要开始在父辈中寻找下一个节点了。
     * 2.1）如果本节点是父节点的左子节点，下一个节点就是父节点。
     * 2.2）如果本节点是父节点的右子节点，下一个节点是祖辈节点或不存在，这里就不再详细展开了。
     *（为啥不考虑左子树？因为左子树不会对它的下一个节点有影响。）
     *
     * 举一反三，如果我们考察的是前序排列，正常情况下，前序排列中，一组序列是：
     * 父节点 、（父节点、左子树、右子树）、（父节点、左子树、右子树）
     * 那么，下一个节点就可能存在于当前节点的左子树或右子树中，或父辈枝干上的某个最近的右子树中。
     * 且，如果当前节点有左子树，那它的下一个节点一定在左子树上。
     * 所以，前序排序寻找下一个节点的突破口是，判断是否有左子树。
     * （前序排序挺有意思的）
     * @param node
     */
    public BinaryTreeNode binaryTreeNext(BinaryTreeNode node) {

        // 1. 如果右子树不为空，next节点一定在右子树中
        if (node.pRight != null) {
            BinaryTreeNode pRight = node.pRight;
            while (pRight.pLeft != null) {
                pRight = pRight.pLeft;
            }
            return pRight;
        }

        // 2. 如果右子树为空（在父辈节点中找）
        // 2.1 本节点没有右子树，且为父节点的左子树
        if (node.pParent != null && node == node.pParent.pLeft) {
            return node.pParent;
        }

        // 2.2 本节点没有右子树，且为父节点的右子树
        BinaryTreeNode pParent = node.pParent;
        while (pParent != null && pParent.pParent != null && pParent != pParent.pParent.pLeft) {
            pParent = pParent.pParent;
        }
        if (pParent != null && pParent.pParent != null) {
            return pParent.pParent;
        }

        return null;
    }


    @Test
    public void test() {
        // 创建中序遍历顺序为{d,b,h,e,i,a,f,c,g}的二叉树
        BinaryTreeNode nodea = new BinaryTreeNode('a');
        BinaryTreeNode nodeb = new BinaryTreeNode('b');
        BinaryTreeNode nodec = new BinaryTreeNode('c');
        BinaryTreeNode noded = new BinaryTreeNode('d');
        BinaryTreeNode nodee = new BinaryTreeNode('e');
        BinaryTreeNode nodef = new BinaryTreeNode('f');
        BinaryTreeNode nodeg = new BinaryTreeNode('g');
        BinaryTreeNode nodeh = new BinaryTreeNode('h');
        BinaryTreeNode nodei = new BinaryTreeNode('i');

        nodea.pParent = null;
        nodea.pLeft = nodeb;
        nodea.pRight = nodec;

        nodeb.pParent = nodea;
        nodeb.pLeft = noded;
        nodeb.pRight = nodee;

        noded.pParent = nodeb;
        noded.pLeft = null;
        noded.pRight = null;

        nodee.pParent = nodeb;
        nodee.pLeft = nodeh;
        nodee.pRight = nodei;

        nodeh.pParent = nodee;
        nodeh.pLeft = null;
        nodeh.pRight = null;

        nodei.pParent = nodee;
        nodei.pLeft = null;
        nodei.pRight = null;

        nodec.pParent = nodea;
        nodec.pLeft = nodef;
        nodec.pRight = nodeg;

        nodef.pParent = nodec;
        nodef.pLeft = null;
        nodef.pRight = null;

        nodeg.pParent = nodec;
        nodeg.pLeft = null;
        nodeg.pRight = null;

        // 查询指定节点的下一个节点
        BinaryTreeNode node = binaryTreeNext(nodei);
        if (node == null) {
            System.out.println("value: no next node! ");
        } else {
            System.out.println("value: " +  node.mValue);
        }

    }

}
