package com.sanjin.www.algorithm.test;

import java.util.LinkedList;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/7/4
 * desc:
 * note:
 */
public class StockQues {

    public static void test() {
        int[] ints = {2, 4, 6, 7};
        StockQues stockQues = new StockQues();
        int result = stockQues.stockQues(ints, 3);
        System.out.println("result: " + result);
    }

    private int stockQues(int[] data, int m) {
        if (data == null || m < 1) {
            return 0;
        }

        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i : data) {
            list.add(i);
        }

        int n = list.size();
        int current = 0;
        int next = 0;
        while (list.size() > 1) {
            for (int i = 1; i < m; i ++) {
                current ++;
                if (current >= n) {
                    current = 0;
                }
            }

            System.out.println("remove: " + current);
            list.remove(current);

            next = current + 1;
            if (next >= n) {
                next = 0;
            }
            current = next;

            n = list.size();
        }

        System.out.println("length: " + n);
        System.out.println("current: " + current);

        return list.get(0);
    }

}
