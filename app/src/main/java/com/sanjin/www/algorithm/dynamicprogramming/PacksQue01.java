package com.sanjin.www.algorithm.dynamicprogramming;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/3/31
 * desc: 0-1背包问题
 * 假设有一个背包，最大承重重量是9，我们有五个不同的物品，每个物品的重量分别是2,2,4,6,3。
 * 求背包中最多能装多重的物品？
 * note: 本问题中，每个物品只能选一次
 */
public class PacksQue01 {

    private final int[] weight = {2, 2, 4, 6, 5};

    private final int n = weight.length;
    private final int w = 16;

    private int maxW = Integer.MIN_VALUE;

    // 回溯算法的备忘录可以用boolean类型记录，减少空间复杂度
    private final boolean[][] mem = new boolean[n][w + 1];

    public void test() {
        bruteForceSearch(0, 0);
        System.out.println("W: " + maxW);
    }

    /**
     * 基于回溯算法的实现实现背包问题
     * 穷举搜索+备忘录
     * @param i
     * @param cw
     */
    private void bruteForceSearch(int i, int cw) {
        // 为什么没有cw > w？因为，cw在增加的时候判断了大小，如果cw>w不会执行到这里来。
        // 为什么没有i > n？因为，i的增加是每次+1，一旦i==n，就会在这里被return了。
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }

        // 备忘录
        if (mem[cw][i]) {
            return;
        }
        mem[cw][i] = true;

        if (cw + weight[i] <= w) { // 剪枝
            bruteForceSearch(i + 1, cw + weight[i]); // 放
        }
        bruteForceSearch(i + 1, cw); // 不放
    }

    /**
     * 状态转移表解法
     */
    private void stateForm() {
        // 记录动态规划的状态，默认值false
        boolean[][] states = new boolean[n][w + 1];

        // 初始化第一行的状态
        states[0][0] = true;
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }

        // 动态规划状态转移
        for (int i = 1; i < n; i ++) {
            for (int j = 0; j <= w; j ++) {
                // 为什么要判断上一阶段的状态？如果画一个状态表，自己去填一下就会知道，
                // 状态表每一行是从0开始的，它包含了填和不填两种情况，即直接在上一阶段的可能情况中去扩展就已经包含了所有情况。
                if (states[i - 1][j]) {
                    states[i][j] = states[i - 1][j]; // 不把第i个物品放入背包，直接继承上一行的状态
                }

                // 可以把这个if放到本for循环中，也可以独立出去，放在里面减少一次for循环
                if ((j == 0 || states[i - 1][j]) && j + weight[i] <= w) {
                    states[i][j + weight[i]] = true; // 把第i个物品放入背包
                }
            }
        }
        /*for (int i = 1; i < n; i ++) {
            for (int j = 0; j <= w; j ++) {
                if (states[i - 1][j]) {
                    states[i][j] = states[i - 1][j]; // 不把第i个物品放入背包，直接继承上一行的状态
                }
            }
            for (int j = 0; j <= w - weight[i]; j ++) {  // 边界为w-weight[i]
                if (states[i - 1][j]) {
                    states[i][j + weight[i]] = true; // 把第i个物品放入背包
                }
            }
        }*/


        // 输出结果（在最后一个阶段的所有情况中遍历最优解，当然，这里我们直接从后往前遍历更快）
        for (int j = w; j >= 0; j --) { // 输出结果即"最大重量值"
            if (states[n - 1][j]) {
                System.out.println("maxWeight: " + j);
                return;
            }
        }
    }

    /**
     * 状态表用一维数组代替二维数组，以优化空间复杂度
     *
     * note:
     * 一位数组优化states的空间复杂度，这件事其实也很简单，我们只要自己亲自画个状态表，并完成填表，
     * 就会发现下一个阶段的状态是在上一个阶段的拓展和新增。且一旦上一阶段利用完后，就不再需要，
     * 且最终寻找最优解时只需要在最后一行遍历。因此我们完全可以用一行来记录整个状态表。
     * 当然，这一点我们从二维数组实现状态的代码中也可以看出，"不放"时，也只是继承了上一阶段的状态而已，
     * 如果只用一行来表示，就可以把这个"不放的继承"省略掉。
     * 甚至，我们在优化states的时候，不仅可以将状态简化为一行，在其他问题中，我们也可以根据实际情况将其简化为一列。
     */
    private void stateForm1() {
        // 优化空间复杂度的状态记录数组，默认值false
        boolean[] states1 = new boolean[w + 1];

        // 初始化第一行
        states1[0] = true;
        if (weight[0] <= w) {
            states1[weight[0]] = true;
        }

        // 动态规划状态转移
        for (int i = 0; i < n; i ++) {
            // j需要从大到小计算，因为从小到大时，当前阶段刚刚因为决策放入而加上去的重量，在后续遍历时会被当作上个阶段的true
            for (int j  = w - weight[i]; j >= 0; j --) { // 边界为w-weight[i]
                if (states1[j]) {
                    states1[j + weight[i]] = true; // 把第i个物品放入背包
                }
            }
        }

        // 输出结果
        for (int j = w; j >= 0; j --) {
            if (states1[j]) {
                System.out.println("maxWeight: " + j);
                return;
            }
        }

    }

    public void testStateFun() {
        int r = stateFun(n-1, w);
        System.out.println("testStateFun result: " + r);
    }

    /**
     * 状态转移方程：
     * if weight[i] > w
     *     return stateFun(i-1, w)
     * else
     *     int v1 = stateFun(i-1, w)
     *     int v2 = stateFUn(i-1, w-weight[i]) + weight[i]
     *     return Math.max(v1,v2)
     *
     * @param i 当前检查的物品的索引
     * @param w 当前情况下，背包的承重限制
     * @return
     */
    private int stateFun(int i, int w) { // 状态转移方程法
        System.out.println("testStateFun i: " + i + " w: " + w);

        if (i < 0) {
          return 0;
        }

        if (w <= 0) {
            return 0;
        }

        if (i == 0) {
            if (weight[i] <= w) {
                return weight[i];
            } else {
                return 0;
            }
        }

        if (weight[i] > w) {
            return stateFun(i-1, w);
        } else {
            int v1 = stateFun(i-1, w);
            int v2 = stateFun(i-1, w-weight[i]) + weight[i];

            return Math.max(v1, v2);
        }
    }
}
