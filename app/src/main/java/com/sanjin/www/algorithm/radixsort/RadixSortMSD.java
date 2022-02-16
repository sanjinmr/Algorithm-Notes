package com.sanjin.www.algorithm.radixsort;

/**
 * 基数排序：最高位优先法
 */
public class RadixSortMSD {

    public static void radixSortMSD(int[] A, int[] B, int begin, int d) {
        int k = 0;

        int[][] bucket = new int[10][B.length];
        int[] order = new int[10];

        for (int num : B) {
            int digit = (num / d) % 10;
            int index = order[digit];
            bucket[digit][index] = num;
            order[digit] ++;
        }

        if (1 == d) {
            //System.out.println("d: " + d);
            for (int i = 0; i < 10; i ++) {
                if (0 != order[i]) {
                    for (int j = 0; j < order[i]; j ++) {
                        A[begin + k] = bucket[i][j];
                        k ++;
                    }
                }
                order[i] = 0;
            }
        } else {
            System.out.println("begin: " + begin + " d: " + d);
            for (int i = 0; i < 10; i ++) {
                if (0 != order[i]) {
                    System.out.print("{");
                    System.out.print(i + "-" + order[i] + ":");

                    int[] temp = new int[order[i]];
                    System.out.print("[");
                    for (int j = 0; j < order[i]; j ++) {
                        temp[j] = bucket[i][j];
                        System.out.print(temp[j] + " ");
                    }
                    System.out.print("]");
                    if ((d / 10) >= 1) {
                        System.out.print(" begin: " + begin + " d: " + d);
                        radixSortMSD(A, temp, begin, d / 10);
                    }
                    begin = begin + temp.length;
                    System.out.print("}  ");
                }
                order[i] = 0;
            }
            System.out.println("============");
        }
    }

    public static void test2() {
        //int[] A=new int[]{73, 22, 93, 0, 43, 55, 14, 28, 1, 65, 89, 77, 66, 44, 56, 5, 7, 75};
        int[] B = new int[]{73, 22, 93, 0, 43, 55, 1, 65, 66, 61, 60, 62, 89, 5, 75};
        //int[] B = new int[]{7, 2, 9, 0, 4, 5, 1, 6, 8, 3, 7};
        //int[] B = new int[]{7, 2, 9, 0, 4, 5, 1, 6, 8, 3, 7, 23, 44, 90, 78};
        int[] A = new int[B.length];
        //int[] A=new int[]{73,22, 93, 43, 55, 14};
        //int[] A=new int[]{1, 6, 13, 34};
        radixSortMSD(A, B,0, 10);
        System.out.print("[");
        for(int num : A){
            System.out.print(num + " 、 ");
        }
        System.out.print("]");
    }

    /**
     *
     * begin: 0 d: 10
     * {0-3:[0 1 5 ] begin: 0 d: 10}
     * {2-1:[22 ] begin: 3 d: 10}
     * {4-1:[43 ] begin: 4 d: 10}
     * {5-1:[55 ] begin: 5 d: 10}
     * {6-5:[65 66 61 60 62 ] begin: 6 d: 10}
     * {7-2:[73 75 ] begin: 11 d: 10}
     * {8-1:[89 ] begin: 13 d: 10}
     * {9-1:[93 ] begin: 14 d: 10}  ============
     * [0 、 1 、 5 、 22 、 43 、 55 、 60 、 61 、 62 、 65 、 66 、 73 、 75 、 89 、 93 、 ]
     *
     */
}
