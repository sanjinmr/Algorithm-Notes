package com.sanjin.www.algorithm.dynamicprogramming;

/**
 * 双十一购物，根据动态规划求出"薅羊毛"的购买列表。详情如下：
 * 淘宝的“双十一”购物节有各种促销活动，比如“满 200 元减 50 元”。
 * 假设你女朋友的购物车中有 n 个（n>100）想买的商品，她希望从里面选几个，在凑够满减条件的前提下，
 * 让选出来的商品价格总和最大程度地接近满减条件（200 元），这样就可以极大限度地“薅羊毛”。
 * 作为程序员的你，能不能编个代码来帮她搞定呢？
 *
 * 对于这个问题，有两种解法：
 * 1）可以用回溯算法，穷举所有的排列组合，看大于等于200并且最接近200的组合是哪个。
 * 2）也可以用动态规划。和0-1背包的动态规划解法差不多，只是把其中的重量换成了这里的价格。
 */
public class ShoppingDouble11 {

    /**
     * 动态规划求解
     *
     * 购物车中有 n 个商品。我们针对每个商品都决策是否购买。
     * 每次决策之后，对应不同的状态集合。我们还是用一个二维数组 states[n][x]，来记录每次决策之后所有可达的状态。
     *
     * items商品价格，n商品个数, w表示满减条件，比如200
     *
     * 代码的前半部分跟 0-1 背包问题没有什么不同，我们着重看后半部分，看它是如何打印出选择购买哪些商品的。
     * 状态 (i, j) 只有可能从 (i-1, j) 或者 (i-1, j-value[i]) 两个状态推导过来。
     * 所以，我们就检查这两个状态是否是可达的，也就是 states[i-1][j]或者 states[i-1][j-value[i]]是否是 true。
     * 如果 states[i-1][j]可达，就说明我们没有选择购买第 i 个商品，如果 states[i-1][j-value[i]]可达，
     * 那就说明我们选择了购买第 i 个商品。我们从中选择一个可达的状态（如果两个都可达，就随意选择一个），
     * 然后，继续迭代地考察其他商品是否有选择购买。
     * @param items
     * @param n
     * @param w
     */
    public static void double11advance(int[] items, int n, int w) {
        // 1、初始化
        boolean[][] states = new boolean[n][3*w+1];//超过3倍就没有薅羊毛的价值了
        states[0][0] = true;  // 第一行的数据要特殊处理
        if (items[0] <= 3*w) {
            states[0][items[0]] = true;
        }

        // 2、动态规划
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = 0; j <= 3*w; ++j) {// 不购买第i个商品
                if (states[i - 1][j]) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= 3*w-items[i]; ++j) {//购买第i个商品
                if (states[i - 1][j]) states[i][j+items[i]] = true;
            }
        }

        // 3、找到可行的最大值
        int j;
        for (j = w; j < 3*w+1; ++j) {
            if (states[n - 1][j]) break; // 输出结果大于等于w的最小值
        }
        if (j == 3*w+1) return; // 没有可行解

        // 4、打印出该值下的对应商品
        for (int i = n-1; i >= 1; --i) { // i表示二维数组中的行，j表示列
            if(j-items[i] >= 0 && states[i - 1][j - items[i]]) {
                System.out.print(items[i] + " "); // 购买这个商品
                j = j - items[i];
            } // else 没有购买这个商品，j不变。
        }
        if (j != 0) System.out.print(items[0]);
    }


}
