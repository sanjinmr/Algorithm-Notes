package com.sanjin.www.algorithm.radixsort;

/**
 * 基数排序
 * 说明：
 * 基数排序(radix sort)又称桶排序（bucket sort），
 * 相对于常见的比较排序，基数排序是一种分配式排序，
 * 即通过将所有数字分配到应在的位置最后再覆盖到原数组完成排序的过程。
 * 详细介绍：
 * http://note.youdao.com/noteshare?id=8feed5a2df1ed7e563ea222e7d02fc3b&sub=6B36C457CF2140B5ACC6F3155EDBCF34
 */
public class RadixSortLSD {

    /**
     *  最低位优先法
     *  note: 其步骤可以浓缩为两步：1、将A存入桶；2、从桶中有序取出，放回A。
     * @param A 待排序数组
     * @param n 待排序数组的长度
     * @param d 表示最高10进制位数（不包含）
     */
    public static void radixSortLSD(int[] A, int n, int d) {

        // 当前对于的位数
        int m = 1;

        // 合并排序时的计数器
        int k = 0;

        // 排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
        int[][] bucket = new int[10][n];

        // 这是一个给十个桶计数的数组，因为这些桶不是有序去添加num的，需要管理十个数字的累计
        // 当然如果分别声明10个int变量来计数也是一样的。只是代码就没这个好看和易维护
        int[] order = new int[10];

        // 因为d不被包含在待排序最高位，所以是>而非>=
        while (m < d) {

            // 第一步：将A的元素存入桶中
            // 将num放进不同的桶中
            for (int num : A) {
                //digit表示在当前位数m下对应的数字。范围为：0-9
                int digit = (num / m) % 10;
                // 根据digit将当前的num放入对应的桶中。
                int index = order[digit];
                bucket[digit][index] = num;
                // 水桶计数器随着元素的add不断累加
                order[digit] ++;
            }

            // 第二步： 从桶中取出，依序放入A中
            // 遍历10个桶
            // 将水桶的元素有序的分别取出来，进行合并
            for (int i = 0; i < 10; i ++) {
                // 如果当前水桶里的计数不为0，则依序取出，并重新赋值到原数组中
                if (order[i] != 0) {
                    for (int j = 0; j < order[i]; j ++) {
                        A[k] = bucket[i][j];
                        k ++;
                    }
                }
                // 取出当前水桶的元素后，将将该桶清空，便于下一个位数排序时再次用到该桶计数
                order[i] = 0;
            }

            // 位数以10的倍数递增
            m *= 10;
            // 当前位全部排序完后，将k设为0，用于下一轮保存位排序结果
            k = 0;
        }
    }

    public static void test2() {
        //int[] A=new int[]{73, 22, 93, 0, 43, 55, 14, 28, 1, 65, 89, 77, 66, 44, 56, 5, 7, 75};
        int[] A = new int[]{73, 22, 93, 0, 43, 55, 1, 65, 89, 5, 75};
        //int[] A=new int[]{73,22, 93, 43, 55, 14};
        //int[] A=new int[]{1, 6, 13, 34};
        radixSortLSD(A, A.length, 100);
        System.out.print("[");
        for(int num : A){
            System.out.print(num + " 、 ");
        }
        System.out.print("]");
    }
}

/**
 补充：
 1
 1   1
 1   2   1
 1   3   3   1
 1   4   6   4   1
 1   5   10 10 5   1
 杨辉三角用二维数组的理解：
 arr[i][j] = arr[i -1][j] + arr[i - 1][j - 1]
 **/