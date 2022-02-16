package com.sanjin.www.algorithm.dynamicprogramming;

/**
 * “杨辉三角”不知道你听说过吗？我们现在对它进行一些改造。
 * 每个位置的数字可以随意填写，经过某个数字只能到达下面一层相邻的两个数字。
 * 假设你站在第一层，往下移动，我们把移动到最底层所经过的所有数字之和，定义为路径的长度。
 * 请你编程求出从最高层移动到最底层的最短路径长度。
 */
public class YangHuiTriangle {

    // 用数组存储"改造后杨辉三角"的值
    private final int[][] matrix = {{5}, {7, 8}, {2,3,4}, {4,9,6,1}, {2,7,9,4,5}};
    // 伪杨辉三角的行数
    private final int n = matrix.length;

    private int minDist = Integer.MAX_VALUE;

    /**
     * 回溯算法，是从上往下穷举的
     * @param i
     * @param j
     * @param dist
     */
    private void f(int i, int j, int dist) { // f(0, 0, matrix[0][0])
        if (i == n - 1) { // 找到最后一层了
            if (minDist > dist) {
                minDist = dist;
            }
            return;
        }

        if (j == 0) {
            f(i + 1, 0, dist + matrix[i + 1][0]);
            f(i + 1, 1, dist + matrix[i + 1][1]);
        } else if (j == matrix[i].length - 1) {
            f(i + 1, j + 1, dist + matrix[i + 1][j + 1]);
            f(i + 1, j, dist + matrix[i + 1][j]);
        } else {
            f(i + 1, j, dist + matrix[i + 1][j]);
            f(i + 1, j + 1, dist + matrix[i + 1][j + 1]);
        }
    }

    /**
     * 每个节点有三个属性：
     * i：行
     * j: 列
     * dist：从最高层到它的最短路径长度
     *
     * 这里每一行（阶段）的状态为dist的值。根据状态推导关系动态计算下一行（阶段）的状态。
     *
     * @return
     */
    private int yangHuiTriangle() {
        // 状态表，value为每个节点的"最短路径长度dist"
        int[][] state = new int[n][n];

        // 初始化第一行状态
        state[0][0] = matrix[0][0];

        // 动态规划，状态转移
        for (int i = 1; i < n; i ++) {
            for (int j = 0; j < matrix[i].length; j ++) {
                if (j == 0) {
                    state[i][j] = state[i-1][j] + matrix[i][j];
                } else if (j == matrix[i].length - 1) {
                    state[i][j] = state[i-1][j-1] + matrix[i][j];
                } else {
                    int leftTop = state[i-1][j-1];
                    int rightTop = state[i-1][j];
                    state[i][j] = Math.min(leftTop, rightTop) + matrix[i][j];
                }
            }
        }

        // 遍历最后一层，查询一个dist最小的值
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < n; i ++) {
            int distance = state[n-1][i];
            if (distance < minDis) minDis = distance;
        }

        return minDis;
    }
}
