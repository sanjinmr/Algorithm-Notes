package com.sanjin.www.algorithm.insertsort.directinsert;

/**
 * 直接插入排序
 * 基本思想：
 * 每次将一个待排序的记录，按其关键字大小，插入到前面已经排好序的子序列中的适当位置。直到全部记录插入完成为止。
 * 设数组为a[0…n-1]。
 * 1. 初始时，a[0]自成1个有序区，无序区为a[1..n-1]。令i=1
 * 2. 将a[i]并入当前的有序区a[0…i-1]中形成a[0…i]的有序区间。
 * 3. i++并重复第二步直到i==n-1。排序完成。
 * note:
 * 1、插入排序的最简单的理解：找到一个待插入的位置K，挪移出位置，将待插入的元素插入K
 * 2、需要知道，该方法默认在前面已排序的位置是有序的。默认索引为0的元素有序。
 * 从索引为1的元素开始让后面的元素有序。
 * 参考链接：http://note.youdao.com/noteshare?id=4b6b92fc1514be35ef1736e981b65602&sub=56BEBD8125EF4EEDB46BAD9CED1765DF
 */
public class DirectInsertSort {

    /**
     * 严格标准的写法：
     * 在有序区间寻找一个比待排序元素小的位置，再挪移，再插入
     * @param a
     * @param n
     */
    public static void insertShort(int [] a,  int n) {
        int i, j, k;

        for (i= 1; i < n; i++) {
            //第一步：搜索
            //在待排元素a[i]前面的a[0...i-1]有序区间从后往前搜索一个合适的位置 ：倒数第一个小于等于a[i]的位置；
            //如果找到了那个位置是j，a[i]的插入位置就是后面的那个位置:j + 1；
            //如果从后往前搜索到最前也没有发现小于等于a[i]的元素，则j的值此时变为-1了，
            //则将待排元素插入：j + 1 = 0，即数组最前面
            for (j = i - 1; j >= 0; j --) {
                if (a[j] > a[i]) {
                    // 如果大于待排元素则继续往前搜索
                } else {
                    // 如果找到倒数第一个小于等于a[i]的元素，则结束遍历有序区间，可以去搬移、插入数据了
                    break;
                }
            }

            //第二步：数据移动
            // 如j==i-1，说明倒数第一个小于等于a[i]的位置就是a[i]前面一个位置的元素，
            // 即没有在有序区间找到一个大于待排元素a[i]的位置，
            // 则不需要移动数据位置了。
            if (j != i -1) {
                // 将待排序的元素记录一下
                int temp = a[i];
                // 将比a[i]大的元素向后移
                // j+1到i-1表示要挪位置的元素们
                // k表示i元素可以选择插入的位置
                for (k = i -1; k > j; k --) {
                    a[k + 1] = a[k];
                }
                // 将待排序的元素放到正确的位置上
                // 挪位置后，k最后还减了1，这里加回来
                a[k + 1]  = temp;
            }
        }
    }

    /**
     * 将insertShort0进行优化：
     * 将搜索和数据后移这两个步骤合并。
     *
     * 基于前面的元素均为有序的原则。
     * 直接判断待排序元素A前面临近的元素是否比自己大。如果大。则说明A需要插入到前面某处。
     * 于是遍历有序区间的元素，找到比自己小的元素（直到寻到开始位置），然后挪位置，并插入。
     * note: 和上面insertSort的区别是：本函数中是先判断的有序区间是否有比自己大的，
     * 然后再找一个比自己小的位置去插入。而insertSort直接寻找比自己小的元素。
     * @param a
     * @param n
     */
    public static void insertSort1(int[] a, int n) {
        int k;
        for(int i = 1; i < n; i ++) {
            // 判断有序区间是否有比自己大的
            if (a[i] < a[i - 1]) {
                int temp = a[i];
                for (k = i - 1; k >= 0 && a[k] > temp; k --) {
                    a[k + 1] = a[k];
                }
                a[k + 1] = temp;
            }
        }
    }

    /**
     * insertSort1进行改变，挪移位置的时候使用while循环，原理相同
     * @param a
     * @param n
     */
    public static void insertSort2(int[] a, int n) {
        int k;
        for (int i = 1; i < n; i ++) {
            if (a[i] < a[i - 1]) {
                int temp = a[i];
                k = i - 1;
                while (k >= 0 && a[k] > temp) {
                    a[k + 1] = a[k];
                    k = k - 1;
                }
                a[k + 1] = temp;
            }
        }
    }

    // -------- 交换替代插入:严格说后面的步骤不算插入排序了，算一种变种 -------------------

    /**
     * 将上面的算法再进行改写：用数据交换代替数据后移。
     * 如果a[k]前一个数据a[k - 1] > a[k]，说明a[k]需要重新和有序区间排序，就交换a[k]和a[k - 1]，
     * 即和比它大的元素交换。再k--，交换，直到a[k-1] <= a[j]。
     *
     * @param a
     * @param n
     */
    public static void insertSort3(int[] a, int n) {
        int k;
        for (int i = 1; i < n; i ++) {
            if (a[i - 1] > a[i]) {
                for (k = i - 1; k >= 0 && a[k] > a[k + 1]; k --) {
                    swap(a, k, k + 1);
                }
            }
        }
    }

    /**
     * 当k=i-1时：
     * a[i - 1] > a[i] 和 a[k] > a[k + 1] 是一样的，于是可以简化为：
     * @param a
     * @param n
     */
    public static void insertSort4(int[] a, int n) {
        int k;
        for (int i = 1; i < n; i ++) {
            for (k = i - 1; k >= 0 && a[k] > a[k + 1]; k --) {
                swap(a, k, k + 1);
            }
        }
    }

    /**
     * 交换
     * @param a
     * @param m
     * @param n
     */
    private static void swap(int[] a, int m, int n) {
        int temp = a[m];
        a[m] = a[n];
        a[n] = temp;
    }
}

/**
 * 思考：插入排序和选择排序的区别是什么？
 * 1)、插入排序是，在已排序区间a[o...i-1]，寻找一个最小位置，并将待排序元素插入其后。
 * 2)、选择排序是，在待排序区间a[i...n-1]，选择一个最小的记录，并和a[i]交换。每次排序需要经过n-i-1次比较
 * （i=0...n-1）
 */
