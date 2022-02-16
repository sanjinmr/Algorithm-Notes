package com.sanjin.www.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * 假设有几种不同的硬币，币值分别为v1、v2......vn（单位是元）。
 * 如果要支付w元，那么最少需要多少硬币？例如，有3种不同的硬币，币值分别为1元、3元和5元。
 * 如果我们要支付9元，那么最少需要3个硬币（如3个3元的硬币）。
 * 说明：每种硬币可以重复，数量没有限制
 *
 * note:
 * 1、用回溯算法实现的话，如果要打印最小硬币数时，有哪些硬币，需要用一个集合去记录，比较麻烦。
 * 如果只是求解最少硬币数，还是很简单的
 */
public class MinCoin {

    private final Integer[] coins = {1, 3, 4, 5, 6, 12};

    private final int n = coins.length;

    private final int bound = 9;

    private int minCount = Integer.MAX_VALUE;

    private int[][] mem = new int[bound + 1][n]; // 备忘录

    public void test() {
        for (int i = 0; i < n; i ++) {
            f(coins[i], 1); // 分别以不同的币值开始
        }

        System.out.println("min-count: " + minCount);
    }

    /**
     *
     * @param amounts 表示已装入的硬币金额总数
     * @param count 表示已选择的硬币数
     */
    private void f(int amounts, int count) { // 只求最少硬币数的回溯算法
        // 递归的时候判断了金额和边界的大小，所以这里不用判断amounts>bound
        if (amounts == bound) {
            if (count < minCount) {
                minCount = count;
            }
            return;
        }

        if (mem[amounts][count] > 0) { // 备忘录
            return;
        } else {
            mem[amounts][count] = 1;
        }

        for (int i = 0; i < n; i ++) { // // 因为每种硬币可以重复，数量没有限制，所以每次选择都for循环所有情况
            if (amounts + coins[i] <= bound) {
                f(amounts + coins[i], count + 1); // 选择该硬币。count是基本数据类型，新的循环不会受影响，所以不用恢复count
            }
            // 硬币选择是可以重复，且没有限制的，所以，不用写"不选"的逻辑。
            // 一般像0-1背包问题，它是每个物品只有一个，我们依次考察每个物品，才可以用"装"、"不装"这样的形式穷举
            //f(amounts, count); // 不选择该硬币
        }
    }

    private final List<List<Integer>> results = new ArrayList<>();

    public void test1() {
        for (int i = 0; i < n; i ++) {
            List<Integer> list = new ArrayList<>();
            list.add(coins[i]);
            f1(list);
        }

        System.out.println("min-count: " + minCount);
        for (List<Integer> list : results) {
            if (list.size() == minCount) {
                printResult(list);
            }
        }
    }

    private void f1(List<Integer> list) { // 记录添加了哪些硬币的回溯算法
        if (getResultValue(list) == bound) {
            results.add(copyList(list));
            if (minCount > list.size()) {
                minCount = list.size();
            }
            return;
        }

        for (int i = 0; i < n; i ++) {
            int amounts = getResultValue(list);
            if (amounts + coins[i] <= bound) {
                list.add(coins[i]);
                f1(list);
                // 因为List是一个对象，在执行了add后，这里应该remove一下让它恢复到未添加的状态再执行下一个循环
                list.remove(coins[i]);
            }
        }
    }

    private int getResultValue(List<Integer> results) {
        int sum = 0;
        for (Integer integer : results) {
            sum += integer;
        }
        return sum;
    }

    private List<Integer> copyList(List<Integer> rawList) {
        List<Integer> list = new ArrayList<>();
        for (Integer integer : rawList) {
            list.add(integer);
        }
        return list;
    }

    private void printResult(List<Integer> results) {
        for (Integer integer : results) {
            System.out.print(integer + "、");
        }
        System.out.println();
    }

    public void test2() {
        int sum = f2(bound);
        System.out.println("min-count: " + sum);
    }

    private final int[] mem1 = new int[bound + 1];

    /**
     * 动态转移方程：
     * f(w) = min(f(w-v1), f(w-v2), f(w-v3)....) + 1
     *
     * @param amounts 表示需要找零的金额
     * @return
     */
    private int f2(int amounts) { // 动态规划的状态转移方程法求解(只计数)
        if (amounts == 0) { // 如果需要的金额为0，则不需要新加硬币了，返回0
            return 0;
        }

        for (int i = 0; i < n; i ++) { // 检查是否有面值等于value的，直接返回1
            if (amounts == coins[i]) {
                return 1;
            }
        }

        if (mem1[amounts] > 0) { // 备忘录拦截
            return mem1[amounts];
        }

        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < n; i ++) { // 计算min(f(w-v1), f(w-v2), f(w-v3)....)
            if (amounts - coins[i] >= 0) { // 判断金额边界
                int v = f2(amounts - coins[i]);
                if (minValue > v) {
                    minValue = v;
                }
            }
        }

        int sum = minValue + 1;

        mem1[amounts] = sum; // 备忘录记录

        return sum;
    }

    public void test3() {
        f3();
    }

    private void f3() { // 动态规划 -- 状态转移表法 -- 打印出所有状态
        int[][] state = new int[bound+1][n];

        for (int i = 0; i <= bound; i ++) {
            for (int j = 0; j < n; j ++) {
                state[0][j] = Integer.MAX_VALUE;
            }
        }
        for (int j = 0; j < n; j ++) {
            state[0][j] = 0;
        }

        for (int i = 1; i <= bound; i ++) {
            for (int j = 0; j < n; j ++) {
                if (i - coins[j] >= 0) {
                    int minNum = Integer.MAX_VALUE;
                    boolean allZero = true; // 第一行全都是0，当全为0时，state等于1。
                    for (int k = 0; k < n; k ++) {
                        int s = state[i-coins[j]][k];
                        if (s < minNum && s != 0) {
                            minNum = s;
                        }
                        if (s > 0) {
                            allZero = false;
                        }
                    }
                    if (allZero) {
                        state[i][j] = 1;
                    } else {
                        state[i][j] = minNum + 1;
                    }
                } else {
                    state[i][j] = 0;
                }
            }
        }

        printState(state);
    }

    private void printState(int[][] states) {
        for (int i = 0; i < states.length; i ++) {
            System.out.print("amount" + i + ":");
            for (int j = 0; j < states[i].length; j ++) {
                System.out.print(" " + states[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

}
