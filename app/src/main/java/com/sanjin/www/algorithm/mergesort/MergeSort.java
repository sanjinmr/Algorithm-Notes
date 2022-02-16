package com.sanjin.www.algorithm.mergesort;

/**
 * 归并排序
 * 分析：
 * 一、合并
 * 归并排序是建立在归并操作上的一种有效的排序算法。
 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 首先考虑下如何将将二个有序数列合并。这个非常简单，只要从比较二个数列的第一个数，谁小就先取谁，
 * 取了后就在对应数列中删除这个数。
 * 然后再进行比较，如果有数列为空，那直接将另一个数列的数据依次取出即可。
 * 二、归并
 *再来看归并排序，其的基本思路就是将数组分成二组A，B，如果这二组组内的数据都是有序的，
 * 那么就可以很方便的将这二组数据进行排序。如何让这二组组内数据有序了？
 * 可以将A，B组各自再分成二组。依次类推，当分出来的小组只有一个数据时，
 * 可以认为这个小组组内已经达到了有序，然后再合并相邻的二个小组就可以了。这样通过先递归的分解数列，
 * 再合并数列就完成了归并排序。
 *
 * 详细分析：
 * http://note.youdao.com/noteshare?id=5ae7fda2098e0543a3476712ca1db8ea&sub=AA3FC71950A94906AF616EDBEF2F5876
 * http://note.youdao.com/noteshare?id=50b30e8a06f8c95a68a5f387d0ffd3a4&sub=72C50F6F03C443548BDB09CE853344C2
 */
public class MergeSort {

    /**
     * 将有二个有序数列a[first...mid]和a[mid+1...last]合并。
     * @param A
     * @param first
     * @param mid
     * @param last
     * @param temp
     */
    private static void mergeArray(int[] A, int first, int mid, int last, int[] temp) {

        int lf = first;
        int le = mid;
        int rf = mid + 1;
        int re = last;

        int k = 0;

        while (lf <= le && rf <= re) {
            if (A[lf] <= A[rf]) {
                temp[k ++] = A[lf ++];
            } else {
                temp[k ++] = A[rf ++];
            }
        }

        while (lf <= le) {
            temp[k ++] = A[lf ++];
        }

        while (rf <= re) {
            temp[k ++] = A[rf ++];
        }

        for (int i = 0; i < k; i ++) {
            A[first + i] = temp[i];
        }
    }

    public static void mergeSort(int[] A, int first, int last, int temp[]) {
        if (first < last) {
            int mid = (first + last) / 2;
            //左边有序
            mergeSort(A, first, mid, temp);
            //右边有序
            mergeSort(A, mid + 1, last, temp);
            //再将二个有序数列合并
            mergeArray(A, first, mid, last, temp);
        }
    }
}
