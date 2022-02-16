package com.sanjin.www.algorithm.selectsort.heapsort;

/**
 * 大根堆的标准实现
 * note: 由于大根堆和体育竞赛中，选出前几名，的场景很相似。故，本算法注释中，将以体育赛事选择前几名举例。
 */
public class MaxHeapSort {

    // ------- 辅助功能函数 ------------------------

    /**
     * parent = (left-1)/2 = (right-1)/2 = (child-1)/2
     * @param i
     * @return
     */
    private int parent(int i) {return (i - 1) / 2;}

    /**
     * 左子节点是奇数
     * @param i
     * @return
     */
    private int left(int i) {return 2 * i + 1;}

    /**
     * 右子节点是偶数
     * @param i
     * @return
     */
    private int right(int i) {return 2 * i + 2;}

    // -------- 排序实现 ---------------------------

    /**
     * 将数组设置为大根堆（各小组比赛，得出第一名）
     * @param a
     * @param parent 本轮比赛的首位选手，即伪第一名
     *               （可能不是所在小组第一名，需要检查，若不是第一名，则重比）
     * @param n
     */
    private void maxHeapAdjust(int[] a, int parent, int n) {
        int temp = a[parent]; // temp保存当前父节点
        int child = left(parent); // 先获得左孩子

        while (child < n) {
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (child + 1 < n && a[child] < a[child + 1]) {
                // 得出小组其他选手中分数最高者
                child ++;
            }
            // 伪第一名和小组其余成绩最高者比较（如果父结点的值已经大于孩子结点的值，则直接结束）
            // 因为默认下面的参赛者是各自小组的第一名，所以只需要比较本轮比赛的首位选手是否是他所在小组的
            // 第一名（因为每一趟得出总的第一名后，将末尾的选手放到了第一名的位置，所以本轮比赛的首位选手
            // 可能不是小组第一，如果不是第一，则需要再比一下）。
            if (temp >= a[child]) {
                break;
            }

            // 如果首位选手不是第一名，更新小组第一的选手身份，它变为下面小组第一，再去比较。
            // 把孩子结点的值赋给父结点
            a[parent] = a[child];
            // 选取孩子结点的左孩子结点,继续向下筛选
            parent = child;
            child = left(child);
        }
        a[parent] = temp;
    }

    public void maxHeapAdjust1(int[] a, int parent, int n) {
        int temp = a[parent];

        for (int child = left(parent); child < n; child = left(child)) {
            if (child + 1 < n && a[child] < a[child + 1]) {
                child ++;
            }

            if (temp >= a[child]) {
                break;
            }

            a[parent] = a[child];
            parent = child;
        }

        a[parent] = temp;
    }

    public void maxHeapAdjust2(int[] array, int parent, int length) {
        int temp = array[parent];

        for (int child = left(parent); child < length; child = left(child)) {
            if (child + 1 < length && array[child] < array[child + 1]) {
                child ++;
            }

            if (array[child] > array[parent]) {
                array[parent] = array[child];

                parent = child;
            } else {
                break;
            }
        }

        array[parent] = temp;
    }

    public void maxHeapAdjust3(int[] a, int parent, int n) {

        for (int child = left(parent); child < n; child = left(child)) {
            if (child + 1 < n && a[child] < a[child + 1]) {
                child ++;
            }

            if (a[child] > a[parent]) {
                int temp = a[parent];
                a[parent] = a[child];
                a[child] = temp;

                parent = child;
            }
        }
    }

    public void maxHeapAdjust4(int[] a, int parent, int n) {
        while (true) {
            // 本次节点和子节点的比较：默认最大值的位置为parent
            int maxPos = parent;

            // 如果left child大于parent
            if (left(parent) < n && a[left(parent)] > a[parent]) {
                maxPos = left(parent);
            }

            // 如果right child大于parent或left
            if (right(parent) < n && a[right(parent)] > a[maxPos]) {
                maxPos = right(parent);
            }

            if (maxPos == parent) { // 如果没有发生交换，则结束本次向下堆化
                break;
            } else { // 如果发生了交换，则交换parent和max child的数据，然后继续向下堆化
                int temp = a[parent];
                a[parent] = a[maxPos];
                a[maxPos] = temp;

                parent = maxPos;
            }
        }
    }

    public void heapSort(int[] a, int n) {

        // 从末位开始，向上选择，得出第一名
        // （循环建立初始堆：从下往上排序）
        for (int i = parent(n - 1); i >= 0; i --) {
			// 从尾部递增向上排序：大的上去，小的下来，尾部边界是数组末尾
            maxHeapAdjust1(a, i, n);
        }

        // 拿出本轮的第一名，再接着选一个第一名，再拿出第一名，再接着选。。。从上往下选。
        // （进行n-1次循环，完成排序：从上往下排序）
        for (int i = n - 1; i > 0; i --) {

            // 拿出本轮得出的第一名选手
            // （最后一个元素和第一元素进行交换）
            int temp = a[i];
            a[i] = a[0];
            a[0] = temp;

            // 从剩下的选手选出第一名
            // （筛选 R[0] 结点，得到i-1个结点的堆
			// 整理，将剩余的元素整理成堆：每次从最上面开始排序，尾部递减）
            maxHeapAdjust1(a, 0, i);
        }
    }
}