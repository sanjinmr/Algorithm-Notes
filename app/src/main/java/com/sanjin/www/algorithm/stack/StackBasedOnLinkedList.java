package com.sanjin.www.algorithm.stack;

/**
 * 基于链表实现的栈。
 *
 * 以链表的最前面位置为栈顶。
 *
 * 为什么不用链表尾结点作栈顶呢？
 * 因为如果是单链表的话，我们删除尾结点的时候，没法知道它的前驱结点。如果要满足这个需求，我们就不得不将其改为双链表，
 * 这样一来会多一个指针，我们还不如使用单链表的头节点作为栈顶。
 *
 * Author: Zheng
 */
public class StackBasedOnLinkedList {
  private Node top = null; // 标记栈顶结点

  public void push(int value) {
    Node newNode = new Node(value, null);
    // 判断是否栈空
    if (top == null) {
      top = newNode;
    } else {
      newNode.next = top;
      top = newNode;
    }
  }

  /**
   * 我用-1表示栈中没有数据。
   */
  public int pop() {
    if (top == null) return -1;
    int value = top.data;
    top = top.next;
    return value;
  }

  public void printAll() {
    Node p = top;
    while (p != null) {
      System.out.print(p.data + " ");
      p = p.next;
    }
    System.out.println();
  }

  private static class Node {
    private int data;
    private Node next;

    public Node(int data, Node next) {
      this.data = data;
      this.next = next;
    }

    public int getData() {
      return data;
    }
  }
}
