package com.sanjin.www.algorithm.stack;

/**
 * 基于数组实现的顺序栈
 *
 * 以数据的末尾位置作为栈顶。因为如果在数组的前面操作会涉及到数据的搬移。
 *
 * 优化：定义一个栈顶的变量是不是更好？数组的栈顶就以下标来充当？
 *
 * from 王铮的《数据结构和算法之美》
 */
public class ArrayStack {

    private String[] items; // 数组（假设存储的数据为字符串）
    private int n;  // 栈的大小
    private int count;  // 栈中的元素个数

    /**
     * 初始化数组，申请一个大小为n的数组
     * @param n
     */
    public ArrayStack(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    /**
     * 入栈操作
     * @param item
     * @return
     */
    public boolean push(String item) {
        // 数组空间不够了，直接返回false，入栈失败
        if (count == n) {
            return false;
        }

        // 将新加入的item放到数组的最后即下标为count的位置，并且count加一
        // 注：不能放在数组前面位置，因为会涉及到数据搬移。同理，出栈也是操作的数组末尾的数据。
        items[count] = item;
        ++count;
        return true;
    }

    /**
     * 出栈操作
     * @return
     */
    public String pop() {
        // 栈为空，则直接返回null
        if (count == 0) {
            return null;
        }

        // 返回下标为count-1的数组元素，并且栈中元素个数count减一
        // 注：以数组末尾为栈顶
        String temp = items[count - 1];
        --count;
        return temp;
    }



}
