package com.sanjin.www.algorithm.countsort;

/**
 * 计数排序
 * 详细介绍：http://note.youdao.com/noteshare?id=a09fcbfb2f2b2e57356ccc33507c6335&sub=CD9D981076AD40879EB9EBE2181DCD1D
 */
public class CountSort {

    /**
     * 计数排序三步：1、构造C数组；2、累计C数组；3、构造B数组。
     * @param A 待排序数组
     * @param n 待排序数组的长度
     * @param k 待排序数组的最大值
     * @return
     */
    public static int[] countSort(int[] A, int n, int k) {

        // 第一步：构造一个C数组，用于统计每个待排序元素的个数
        int[] C = new int[k + 1];
        // 统计A中各元素个数，存入C数组
        for (int i = 0; i < n; i ++) {
            int a = A[i];
            C[a] ++;
        }

        // 第二步：累计C数组的各元素
        // 将每个i位置的元素大小改成C数组前i项和
        int sum = 0;
        // 修改C数组：累计
        for (int i = 0; i <= k; i ++) {
            sum = sum + C[i];
            C[i] = sum;
        }

        // 第三步：将A的元素按序放入B中。
        // 构造B数组
        int[] B = new int[n];
        // 遍历A数组，充实B数组
        // 从A的末尾开始取出，请学习者自己思考一下为什么。答案在参考链接中作了说明。
        for (int i = n - 1; i >= 0; i --) {
            int a = A[i];
            int index = C[a] - 1;
            //将A中该元素放到排序后数组B中指定的位置
            B[index] = a;
            //将C中该元素-1，方便计算下一个同样大小的元素的位置
            C[a] --;
        }

        return B;
    }

    public static void test() {
        int[] A = new int[]{2, 5, 3, 0, 2, 3, 0, 3};
        int[] B = countSort(A, A.length, 5);
        for(int i = 0; i < A.length; i++) {
            System.out.println((i + 1) + "th:" + B[i]);
        }
    }
}
