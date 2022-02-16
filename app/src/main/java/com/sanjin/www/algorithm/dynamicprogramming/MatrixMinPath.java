package com.sanjin.www.algorithm.dynamicprogramming;

/**
 * 矩阵最小路径问题
 *
 * 问题描述：
 * 假设我们有一个 n 乘以 n 的矩阵 w[n][n]。矩阵存储的都是正整数。
 * 棋子起始位置在左上角，终止位置在右下角。我们将棋子从左上角移动到右下角。
 * 每次只能向右或者向下移动一位。从左上角到右下角，会有很多不同的路径可以走。
 * 我们把每条路径经过的数字加起来看作路径的长度。那从左上角移动到右下角的最短路径长度是多少呢？
 *
 * 递推公式：min_dist(i, j) = w[i][j] + min(min_dist(i, j-1), min_dist(i-1, j))
 *
 */
public class MatrixMinPath {


    private int minDist = Integer.MAX_VALUE; // 全局变量或者成员变量
    private int[][] values = {{1,3,5,9}, {2,1,3,4}, {5,2,6,7}, {6,8,4,3}};
    private int n = values.length;

    private int[][] mem = new int[4][4];

    /**
     * 回溯算法
     * @param i
     * @param j
     * @param dist
     */
    // 调用方式：minDistBT(0, 0, 0);
    // i 表示行，j 表示列
    public void minDistBT(int i, int j, int dist) { // 这里dist表示上一个节点的dist值
        dist += values[i][j]; // 计算到[i][j]节点的dist
        if (i == n - 1 && j == n - 1) {
            if (dist < minDist) minDist = dist;
            return;
        }
        if (i < n - 1) { // 往下走，更新i=i+1, j=j。并传入上一个节点的dist
            minDistBT(i + 1, j, dist);
        }
        if (j < n - 1) { // 往右走，更新i=i, j=j+1。并传入上一个节点的dist
            minDistBT(i, j+1, dist);
        }
    }

    /**
     * 状态转移表法
     * @param matrix
     * @param n
     * @return
     */
    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int j = 0; j < n; ++j) { // 初始化states的第一行数据
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; ++i) { // 初始化states的第一列数据
            sum += matrix[i][0];
            states[i][0] = sum;
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                states[i][j] =
                        matrix[i][j] + Math.min(states[i][j-1], states[i-1][j]);
            }
        }

        return states[n-1][n-1];
    }

    /**
     * 状态转移方程法
     *
     * 状态转移方程：
     * min_dist(i, j) = w[i][j] + min(min_dist(i, j-1), min_dist(i-1, j))
     *
     * @param i
     * @param j
     * @return
     */
    //  i 表示行，j 表示列
    public int minDist(int i, int j) { // 调用minDist(n-1, n-1);
        if (i == 0 && j == 0) return values[0][0]; // 边界

        if (mem[i][j] > 0) return mem[i][j]; // 备忘录拦截

        int minLeft = Integer.MAX_VALUE;
        if (j-1 >= 0) {
            minLeft = minDist(i, j-1);
        }

        int minUp = Integer.MAX_VALUE;
        if (i-1 >= 0) {
            minUp = minDist(i-1, j);
        }

        int currMinDist = values[i][j] + Math.min(minLeft, minUp);

        mem[i][j] = currMinDist; //备忘录记录

        return currMinDist;
    }

}
