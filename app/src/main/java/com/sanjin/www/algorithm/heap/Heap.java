package com.sanjin.www.algorithm.heap;

/**
 * 定义一个数据结构 --  堆
 *
 * 本堆的数据结构设计中，空置数组索引为0的地方
 *
 */
public class Heap {

    private int[] a; // 数组，从下标1开始存储数据
    private int n; // 堆可以存储的最大的数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        a = new int[capacity + 1]; // "王铮"的堆中第一个位置空着，所以他的实现+1了
        n = capacity;
        count = 0;
    }

    /**
     * 因为空置0位，所以：
     * left=2*parent
     * right=2*parent+1
     * parent=left/2或parent=(right-1)/2=right/2，所以统称parent=child/2
     *
     * 不空置0的数组，即堆的二叉树从0开始。它每个左子节点都是奇数，右子节点都是偶数。所以parent=(left-1)/2=(right-1)/2=(child-1)/2
     * 要空置0的数组，即堆的二叉树从1开始。它每个左子节点都是偶数，右子节点都是奇数。所以parent=left/2=(right-1)/2=right/2=child/2
     *
     * @param child
     * @return
     */
    private int parent(int child) {
        return child / 2;
    }

    /**
     * 左子节点是偶数
     * @param parent
     * @return
     */
    private int left(int parent) {
        return 2 * parent;
    }

    /**
     * 右子节点是奇数
     * @param parent
     * @return
     */
    private int right(int parent) {
        return 2 * parent + 1;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void insert(int data) {
        if (count >= 0) {
            return; // 堆满了
        }
        ++count;
        a[count] = data;
        int child = count; // 标记新加的需要堆化的child
        // 由下向上堆化。
        // 这里没有判断左右节点哪个大，是因为插入数据前，数组已经是堆化后的。
        // 在末尾添加一个数据，只需要判断这个数据和上面的parent们的大小， 因为parent一定比已经堆化的child大
        while (parent(child) > 0 && a[child] > a[parent(child)]) { // 注意不是>=0，因为"王铮"设计的0位置空
            swap(a, child, parent(child));
            child = parent(child);
        }
    }

    public void removeMax() {
        if (count == 0) {
            return; // 堆中没有数据
        }
        a[1] = a[count];
        a[count] = 0; // 将数据移到堆顶后，将其原位置的设为0
        --count;
        heapify(a, count, 1);
    }

    /**
     * 自上而下的堆化
     *
     * 这种方式是"王铮"的课程中的实现。还有一种实现，是for循环。
     *
     * @param a
     * @param n
     * @param parent
     */
    private void heapify(int[] a, int n, int parent) {
        while (true) {
            // 在parent/left/right中找一个最大值的位置
            int maxPox = parent;
            if (left(parent) <= n && a[parent] < a[left(parent)]) {
                maxPox = left(parent);
            }
            if (right(parent) <= n && a[maxPox] < a[right(parent)]) {
                maxPox = right(parent);
            }

            // 如果最大值是parent，则说明堆化完成
            if (maxPox == parent) {
                break;
            }

            // 如果最大值不是当前的parent，则交换数据，继续向下堆化
            swap(a, parent, maxPox);
            parent = maxPox;
        }
    }



}
