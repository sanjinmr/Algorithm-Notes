package com.sanjin.www.algorithm.insertsort.shellsort;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2019/3/14
 * desc: 无log
 * note: AlgorithmShell的测试类
 */
public class AlgorithmShellTest {

    /**
     * 插入排序： 普通插入排序的步长改为了gap
     * @param a
     * @param n
     * @param i
     * @param gap
     */
    public static void directInsertSort(int[] a, int n, int i, int gap) {
        System.out.println(" ,gap: " + gap);
        for (int j = i + gap; j < n; j += gap) {
            System.out.print(" ,j: " + j);
            if (a[j - gap] > a[j]) {
                int temp = a[j];
                int k = j - gap;
                System.out.print(" ,k: " + k);
                while (k >= 0 && a[k] > temp) {
                    a[k +gap] = a[k];
                    k -= gap;
                    System.out.println(" ,k1: " + k);
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
        int sum = 0;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            System.out.println("\n<=========>");
            System.out.println("第" + sum++ + "次增量： " + gap);
            for (int i = 0; i < gap; i ++) {
                System.out.println("第" + i + "次排序，增量： " + gap);
                directInsertSort(a, n, i, gap);
            }
        }
    }
}
