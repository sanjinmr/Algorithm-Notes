package com.sanjin.www.algorithm.dynamicprogramming;

/**
 * 对0-1背包问题稍加改造，如果每个物品不仅重量不同，价值也不同。
 * 那么，如何在不超过背包承载的最大重量的前提下，让背包中所装物品的总价值最大？
 *
 * note:
 * 0-1背包价值问题，不方便用背包优化回溯算法
 *
 */
public class PacksQue01Plus {

    private int maxV = Integer.MIN_VALUE; // 结果放到maxV中
    private final int[] weight = {2, 2, 4, 6, 3}; // 物品的重量
    private final int[] values = {3, 4, 8, 9, 6}; // 物品的价值

    private final int n = 5; // 物品的个数
    private final int w = 9; // 背包可承载的最大重量

    /**
     * 回溯算法求解
     * @param i
     * @param cw
     * @param cv
     */
    public void f(int i, int cw, int cv) { // 调用f(0, 0, 0)
        if (cw == w || i == n) { // cw == w表示装满了，i == n表示物品都考察完了
            if (cv > maxV) {
                maxV = cv; // 记录最大的总价值
            }
            return;
        }

        f (i + 1, cw, cv); // 选择不装第i个物品
        if (cw + weight[i] <= w) { // 过滤掉超过重量限制的无效探测
            f(i + 1, cw + weight[i], cv + values[i]); // 选择装第i个物品
        }
    }

    public int knapsack3() {  // 状态转移表
        // 记录动态规划的状态
        int[][] states = new int[n][w+1];

        // 初始化states
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < w+1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = values[0];
        }

        //动态规划，状态转移
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= w; ++j) { // 不选择第i个物品
                if (states[i-1][j] >= 0) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= w-weight[i]; ++j) { // 选择第i个物品(边界为w-weight[i])
                // 为什么要判断上一阶段的状态？如果画一个状态表，自己去填一下就会知道，
                // 状态表每一行是从0开始的，它包含了填和不填两种情况，即直接在上一阶段的可能情况中去扩展就已经包含了所有情况。
                if (states[i-1][j] >= 0) {
                    int v = states[i-1][j] + values[i];
                    if (v > states[i][j+weight[i]]) { // 只记录最大价值的值
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }

        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n-1][j] > maxvalue) maxvalue = states[n-1][j];
        }

        return maxvalue;
    }

    public int knapsack31() {  // 一个数组的状态转移表
        // 记录动态规划的状态
        int[] states = new int[w+1];

        // 初始化states
        for (int j = 0; j < w+1; ++j) {
            states[j] = -1;
        }
        states[0] = 0;
        if (weight[0] <= w) {
            states[weight[0]] = values[0];
        }

        //动态规划，状态转移
        for (int i = 1; i < n; ++i) {
            // j 需要从大到小来处理。如果我们按照 j 从小到大处理的话，会出现 for 循环重复计算的问题。
            for (int j = w - weight[i]; j >= 0; j --) { // 选择第i个物品(边界为w-weight[i])
                if (states[j] >= 0) {
                    int v = states[j] + values[i];
                    if (v > states[j+weight[i]]) { // 只记录最大价值的值
                        states[j+weight[i]] = v;
                    }
                }
            }
        }

        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[j] > maxvalue) maxvalue = states[j];
        }

        return maxvalue;
    }

    public void testStateFun() {
        int r = stateFun(n-1, w);
        System.out.println("result: " + r);
    }

    /**
     * 状态转移方程：
     * if (weight[i] > w)
     *     f(i,w)=f(i-1, w)
     * else
     *     f(i, w)=max(f(i-1, w), f(i-1, w-weight[i]) + value[i])
     *
     * @param i 当前考察的物品索引
     * @param w 当前的背包承重限制
     * @return
     */
    private int stateFun(int i, int w) { // 状态转移方程法
        if (i < 0) {
            return 0;
        }

        if (w <= 0) {
            return 0;
        }

        if (i == 0) {
            if (weight[i] > w) {
                return values[i]; // 是values
            } else {
                return 0;
            }
        }

        if (weight[i] > w) {
            return stateFun(i-1, w);
        } else {
            int v1 = stateFun(i-1, w);
            int v2 = stateFun(i-1, w-weight[i]) + values[i]; // 加values
            return Math.max(v1, v2);
        }
    }

}
