package com.sanjin.swordfingeralgorithm2.algorithm;

import org.junit.Test;

import java.util.Stack;


/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/1/13
 * desc:
 * note:
 */
public class LinkedListBase {

    static class Node {
        int value;
        Node next;
    }

    /**
     * 在链表尾部添加节点
     * @param nodes
     * @param value
     * @return
     */
    Node addToTail(Node nodes, int value) {
        Node newNode = new Node();
        newNode.value = value;
        newNode.next = null;

        if (nodes == null) {
            nodes = newNode;
            return nodes;
        }

        Node pNode = nodes;
        while (pNode != null && pNode.next != null) {
            pNode = pNode.next;
        }
        pNode.next = newNode;

        return nodes;
    }

    /**
     * 删除指定值的节点
     * @param headNode
     * @param value
     * @return
     */
    Node removeNode(Node headNode, int value) {
        // 1. 判断链表不能为空
        if (headNode == null) {
            return headNode;
        }

        if (headNode.value == value) {
            // 2.处理需删除节点为头节点的情况
            headNode = headNode.next;
        } else {
            // 3. 处理需删除节点为非头结点的情况
            // 3.1 先建立一个探针
            Node pNode = headNode;
            // 3.2 探测符合被删除标准的节点（注：一般探测next指针）
            while (pNode.next != null && pNode.next.value != value) {
                pNode = pNode.next;
            }
            // 3.3 删除探测到的节点。但需要排除整个链表没有探测到符合删除标准的节点的情况
            if (pNode.next != null && pNode.next.value == value) {
                pNode.next = pNode.next.next;
            }
        }

        return headNode;
    }

    /**
     * 反转打印链表 -- 栈+遍历
     * @param headNode
     */
    void reversalPrint(Node headNode) {
        Stack<Node> nodes = new Stack<>();

        // 使用探针遍历，以免改变链表原本结构
        Node pNode = headNode;
        while (pNode != null) {
            nodes.push(pNode);
            pNode = pNode.next;
        }

        while (!nodes.empty()) {
            pNode = nodes.pop();
            System.out.println("node: " + pNode.value);
        }
    }

    /**
     * 反转打印链表 -- 递归的方式
     * @param headNode
     */
    void reversalPrintRecursion(Node headNode) {
        if (headNode != null) {
            if (headNode.next != null) {
                reversalPrintRecursion(headNode.next);
            }
            System.out.println("node: " + headNode.value);
        }
    }

    @Test
    public void test() {
        testReversalPrint();
    }

    private void testReversalPrint() {
        Node headNode = new Node();
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();

        headNode.value = 1;
        headNode.next = node1;
        node1.value = 2;
        node1.next = node2;
        node2.value = 3;
        node2.next = node3;
        node3.value = 4;
        node3.next = null;

        //reversalPrint(headNode);
        reversalPrintRecursion(headNode);
    }

    private void testAdd() {
        Node nodes = null;
        //nodes = new Node();
        //nodes.value = 11;

        nodes = addToTail(nodes, 12);
        nodes = addToTail(nodes, 13);
        nodes = addToTail(nodes, 14);

        while (nodes != null) {
            System.out.println("traversal ListNode: " + nodes.value);
            nodes = nodes.next;
        }
    }

    private void testDelete() {
        Node headNode = new Node();
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();

        headNode.value = 1;
        headNode.next = node1;
        node1.value = 2;
        node1.next = node2;
        node2.value = 3;
        node2.next = node3;
        node3.value = 4;
        node3.next = null;

        headNode = removeNode(headNode, 2);

        while (headNode != null) {
            System.out.println("traversal ListNode: " + headNode.value);
            headNode = headNode.next;
        }
    }
}
