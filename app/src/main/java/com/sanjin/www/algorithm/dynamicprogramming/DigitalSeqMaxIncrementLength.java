package com.sanjin.www.algorithm.dynamicprogramming;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我们有一个数字序列包含 n 个不同的数字，如何求出这个序列中的最长递增子序列长度？
 * 比如 2, 9, 3, 6, 5, 1, 7 这样一组数字序列，它的最长递增子序列就是 2, 3, 5, 7，所以最长递增子序列的长度是 4。
 */
public class DigitalSeqMaxIncrementLength {

    private final int[] digital = {2, 9, 3, 6, 5, 1, 7};
    private final int n = digital.length;

    private int maxSum = 0;

    public void test() { // 回溯算法的测试
        for (int i = 0; i < n; i ++) {
            if (i == n - 1) {
                if (maxSum < 1) {
                    maxSum = 1;
                }
            } else {
                f(digital[i],  i + 1, 1);
            }
        }

        System.out.println("maxSum: " + maxSum);
    }

    private void f(int preValue, int nextIndex, int preSum) { // 回溯算法的实现
        if (nextIndex == n) {
            if (preSum > maxSum) {
                maxSum = preSum;
            }
            return;
        }

        for (int i = nextIndex; i < n; i ++) {
            if (digital[i] >= preValue) {
                f(digital[i], i + 1, preSum + 1);
            } else {
                f(preValue, i + 1, preSum);
            }
        }
    }

    public void test1() {
        f1();
    }

    private void f1() { // 状态转移表法的实现
        int[] states = new int[n];

        // 初始化第一行
        states[0] = 1;

        // 状态转移
        for (int i = 1; i < n; i ++) {
            int maxTemp = 0;
            for (int j = 0; j < i; j ++) {
                if (states[j] > maxTemp && digital[i] >= digital[j]) {
                    maxTemp = states[j];
                }
            }
            states[i] = maxTemp + 1;
        }

        // 统计结果
        int result = 0;
        for (int s : states) {
            if (s > result) {
                result = s;
            }
        }
        System.out.println("result: " + result);
    }

    public void test2() {
        int result = f2(n-1);
        System.out.println("result: " + result);
    }

    private int f2(int i) { // 状态转移方程法
        if (i == 0) {
            return 1;
        }

        int maxTemp = 0;

        for (int j = 0; j < i; j ++) {
            int v = f2(j);
            if (maxTemp < v && digital[i] >= digital[j]) {
                maxTemp = v;
            }
        }

        System.out.println("resulting i: " + i + " maxTemp: " + maxTemp +  " result: " + (maxTemp + 1));

        return maxTemp + 1;
    }

    private List<List<Integer>> results = new ArrayList<>();

    public void test3() {
        for (int i = 0; i < n; i ++) {
            List<Integer> list = new ArrayList<Integer>();
            list.add((Integer) digital[i]);
            f3(digital[i],  i + 1, list);
        }

        System.out.println("maxSum: " + maxSum);

        List<List<Integer>> resultsReal = new ArrayList<>();
        for (List<Integer> result : results) {
            if (result.size() == maxSum) {
                if (!checkIsRepeat(resultsReal, result)) { // 过滤重复的result
                    System.out.println();
                    resultsReal.add(result);
                    for (Integer i : result) {
                        System.out.print(i + " 、 " );
                    }
                }
            }
        }
    }

    private void f3(int preValue, int nextIndex, List<Integer> list) {
        if (nextIndex == n) {
            if (list.size() >= maxSum) { // 相等也加入results
                maxSum = list.size();
                results.add(copyList(list));
            }
            return;
        }

        for (int i = nextIndex; i < n; i ++) { // 遍历后面的元素
            if (digital[i] >= preValue) { // 加入
                list.add((Integer) digital[i]);
                f3(digital[i], i + 1, list);
                list.remove((Integer) digital[i]);
            } else {  // 不加入
                f3(preValue, i + 1, list);
            }
        }
    }

    private ArrayList<Integer> copyList(List<Integer> list) {
        ArrayList<Integer> listNew = new ArrayList<>();
        for (Integer i : list) {
            listNew.add(i);
        }
        return listNew;
    }

    private boolean checkIsRepeat(List<List<Integer>> parent, List<Integer> result) {
        for (List<Integer> c : parent) {
            if (c.size() == result.size()) {
                if (result.containsAll(c)) {
                    return true;
                }
            }
        }
        return false;
    }
}
