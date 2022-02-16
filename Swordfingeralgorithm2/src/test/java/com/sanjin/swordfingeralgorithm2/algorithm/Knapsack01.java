package com.sanjin.swordfingeralgorithm2.algorithm;

import org.junit.Test;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/1/19
 * desc:
 * note:
 */
public class Knapsack01 {

    public int maxW = Integer.MIN_VALUE;

    public void f(int i, int cw, int[] items, int n, int w) {
        System.out.println("test i: " + i + " cw: " + cw);
        if (cw == w || i == n) {
            if (cw > maxW) {
                maxW = cw;
            }
            System.out.println("test return i: " + i + " cw: " + cw);
            return;
        }

        f(i+1, cw, items, n, w);

        int nweCw = cw + items[i];
        if (nweCw <= w) {
            System.out.println("test i: " + (i) + " nweCw: " + nweCw);
            f(i + 1, nweCw, items, n, w);
        } else {
            System.out.println("test i: " + (i) + "  nweCw: " + nweCw + " > 100");
        }
    }

    public void knapsack(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }

        knapsack(i+1, cw, items, n, w);

        if (cw + items[i] <= w) {
            int nweCw = cw + items[i];
            knapsack(i + 1, nweCw, items, n, w);
        }
    }

    @Test
    public void test() {
        int[] items = {20, 30, 40, 50, 60};
        f(0, 0, items, 5, 100);
    }
}
