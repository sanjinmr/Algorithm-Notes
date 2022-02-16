package com.sanjin.www.algorithm.insertsort.shellsort;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2019/3/14
 * desc: 附带log。用于分析
 * note: 对AlgorithmShell.java封装一下
 */
public class AlgorithmShell1 {

    public static void shellSort(int[] a, int n) {
        for (int gap = n / 2; gap > 0;  gap = gap / 2) {
            groupInGap(a, n, gap);
        }
    }

    public static void groupInGap(int[] a, int n, int gap) {
        for (int i = 0; i < gap; i ++) {
            directInsertSort(a, n, i, gap);
        }
    }

    public static void directInsertSort(int[] a, int n, int i, int gap) {
        for (int j = i + gap; j < n; j = j + gap) {
            if (a[j - gap] > a[j]) {
                int temp = a[j];
                int k = j - gap;
                while (k >= 0 && a[k] > temp) {
                    a[k + gap] = a[k];
                    k = k - gap;
                }
                a[k + gap] = temp;
            }
        }
    }
}

/**
 日志：
 开始希尔排序
 整体根据增量第0次全局分组，增量为： 4
 第[0]组===============
 [80 20]
 插入排序比较：0 > 4
 比较0 > 4
 交换0和4
 第[1]组===============
 [30 10]
 插入排序比较：1 > 5
 比较1 > 5
 交换1和5
 第[2]组===============
 [60 50]
 插入排序比较：2 > 6
 比较2 > 6
 交换2和6
 第[3]组===============
 [40 70]
 插入排序比较：3 < 7
 不用交换
 整体根据增量第1次全局分组，增量为： 2
 第[0]组===============
 [20 50 80 60]
 插入排序比较：0 < 2
 不用交换
 插入排序比较：2 < 4
 不用交换
 插入排序比较：4 > 6
 比较4 > 6
 交换4和6
 比较2 < 6
 交换结束
 第[1]组===============
 [10 40 30 70]
 插入排序比较：1 < 3
 不用交换
 插入排序比较：3 > 5
 比较3 > 5
 交换3和5
 比较1 < 5
 交换结束
 插入排序比较：5 < 7
 不用交换
 整体根据增量第2次全局分组，增量为： 1
 第[0]组===============
 [20 10 50 30 60 40 80 70]
 插入排序比较：0 > 1
 比较0 > 1
 交换0和1
 插入排序比较：1 < 2
 不用交换
 插入排序比较：2 > 3
 比较2 > 3
 交换2和3
 比较1 < 3
 交换结束
 插入排序比较：3 < 4
 不用交换
 插入排序比较：4 > 5
 比较4 > 5
 交换4和5
 比较3 > 5
 交换3和4
 比较2 < 5
 交换结束
 插入排序比较：5 < 6
 不用交换
 插入排序比较：6 > 7
 比较6 > 7
 交换6和7
 比较5 < 7
 交换结束

 排序后：
 10; 20; 30; 40; 50; 60; 70; 80;

 Process finished with exit code 0
 **/