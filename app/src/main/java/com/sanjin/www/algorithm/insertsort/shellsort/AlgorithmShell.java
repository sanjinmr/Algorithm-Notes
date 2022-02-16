package com.sanjin.www.algorithm.insertsort.shellsort;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2019/3/14
 * desc: 希尔排序
 * note: 希尔排序实际上就是对直接插入排序的一个升级：
 * 将数组分为若干组进行直接插入排序，只是将插入排序的步长改为而已！
 * 希尔排序退化到极点，即步长只是1，就等于直接插入排序了。
 */
public class AlgorithmShell {

    /**
     * 插入排序： 普通插入排序的步长改为了gap
     * @param a
     * @param n
     * @param i
     * @param gap
     */
    public static void directInsertSort(int[] a, int n, int i, int gap) {
        for (int j = i + gap; j < n; j += gap) {
            if (a[j - gap] > a[j]) {
                int temp = a[j];
                int k = j - gap;
                while (k >= 0 && a[k] > temp) {
                    a[k +gap] = a[k];
                    k -= gap;
                }
                a[k + gap] = temp;
            }
        }
    }

    /**
     * 插入排序： 普通插入排序的步长改为了gap
     * 将插入排序部分的while循环改为for循环，本质相同
     * @param a
     * @param n
     * @param i
     * @param gap
     */
    public static void directInsertSort1(int[] a, int n, int i, int gap) {
        int k;
        for (int j = i + gap; j < n; j += gap) {
            if (a[j - gap] > a[j]) {
                int temp = a[j];
                for (k = j - gap; k >=0 && a[k] > temp; k = k - gap) {
                    a[k +gap] = a[k];
                }
                a[k + gap] = temp;
            }
        }
    }

    public static void shellSort(int[] a, int n) {
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = 0; i < gap; i ++) {
                directInsertSort(a, n, i, gap);
            }
        }
    }

    /**
     * 用最原始的直接插入排序写一个希尔排序
     * @param a
     * @param n
     */
    public static void shellSort1(int[] a, int n) {
        int j, k;
        for (int gap = n / 2; gap > 0; gap = gap / 2) { // shell分组
            for (int gi = 0; gi < gap; gi ++) { // 遍历每一组
                for (int i = gi + gap; i < n; i = i + gap) { // 对每一组进行直接插入排序
                    for (j = i - gap; j >= 0; j = j - gap) {
                        if (a[j] <= a[i]) {
                            break;
                        }
                    }

                    if (j != i - gap) {
                        int temp = a[i];
                        for (k = i - gap; k > j; k = k - gap) {
                            a[k + gap] = a[k];
                        }
                        a[k + gap] = temp;
                    }
                }
            }
        }
    }

    public static void shellSort2(int[] a, int n) {
        int k;
        for (int gap = n / 2; gap > 0; gap = gap / 2) { // shell分组
            for (int gi = 0; gi < gap; gi ++) { // 遍历每一组
                for (int i = gi + gap; i < n; i = i + gap) { // 对每一组进行直接插入排序
                    if (a[i - gap] > a[i]) {
                        int temp = a[i];
                        for (k = i - gap; k >= 0 && a[k] > temp; k = k - gap) {
                            a[k + gap] = a[k];
                        }
                        a[k + gap] = a[k];
                    }
                }
            }
        }
    }

    /**
     * 在这里放一个最原始的步长为1的直接插入排序以作参考
     * @param a
     * @param n
     */
    public static void insertShort(int [] a,  int n) {
        int i, j, k;

        for (i= 1; i < n; i++) {
            for (j = i - 1; j >= 0; j --) {
                if (a[j] <= a[i]) {
                    break;
                }
            }

            if (j != i -1) {
                int temp = a[i];
                for (k = i -1; k > j; k --) {
                    a[k + 1] = a[k];
                }
                a[k + 1]  = temp;
            }
        }
    }
}
