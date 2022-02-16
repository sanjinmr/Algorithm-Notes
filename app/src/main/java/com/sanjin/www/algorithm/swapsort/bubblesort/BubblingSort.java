package com.sanjin.www.algorithm.swapsort.bubblesort;

/**
 * 冒牌排序思想：
 * 设数组的长度为N：
 * （1）比较前后相邻的二个数据，如果前面的数据大于后面的数据，就将这两个数据交换；
 * （2）这样对数组的第0个数据到N-1个数据进行一次遍历后，最大的一个数据就沉到数组最后面去了：
 * （3）一共比较n-1次
 */
public class BubblingSort {

    // -------- 功能函数 -------------------------------------

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // ------- 算法实现 --------------------------------------

    /**
     * 这是一个最基本的冒泡排序。
     * @param a
     * @param n
     */
    public static void bubbleSort1(int[] a, int n) {
        int i, j;
        // 表示n次排序过程；n - i表示每次交换的尾边界，i++表示尾边界递减
        for (i = 0; i < n; i ++) {
            for (j = 1; j < n - i; j ++) {
                // 前面的元素大于后面的元素，则交换
                if (a[j - 1] > a[j]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    /**
     * 第一次优化：
     * 下面开始考虑优化
     * 如果对于一个本身有序的序列。或者序列后面一大部分都是有序的序列，上面的算法就会浪费很多时间开销
     * 这里设置一个标志flag，如果这一趟发生了交换，则为true，否则为false。
     * 明显，如果有一趟没有发生交换，则说明排序已经完成了。
     */
    public static void bubbleSort2(int[] a, int n) {
        int j;

        // k表示每次遍历交换的尾边界
        int k = n;

        // flag表示是否交换过数据：true表示发生了交换，false表示未发生交换
        // 是否发生过交换也是排序循环是否继续的判断依据
        boolean flag = true;

        while (flag) {

            // 每次开始遍历前，都设置为未交换过
            // 如果没有交换，则while排序/循环结束
            flag = false;

            for (j = 1; j < k; j ++) {
                // 前面的数据大于后面的数据就交换
                if (a[j - 1] > a[j]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;

                    // 表示交换过数据
                    flag = true;
                }
            }
            // 减少一次遍历的尾边界
            k --;
        }
    }

    /**
     * bubbleSort2变体
     *
     * 当然，使用for循环同样是都可以的。
     *
     * 对bubbleSort2进行改造：while循环改为for循环
     * @param a
     * @param n
     */
    public static void bubbleSort21(int[] a, int n) {
        // flag记住是否发生了交换。开始的时候，默认需要交换
        boolean flag = true;
        for (int i = 0; flag && i < n; i ++) {
            flag = false;
            for (int j = 1; j < n - i; j ++) {
                if (a[j - 1] > a[j]) {
                    swap(a, j - 1 , j);

                    flag = true;
                }
            }
        }
    }

    /**
     * bubbleSort2变体
     * 当然bubbleSort21是比较当前元素的和前面的元素的大小。
     * 我们也可以比较当前的元素和后面的元素的大小：
     * @param a
     * @param n
     */
    public static void bubbleSort22(int[] a, int n) {
        System.out.println("   bubbleSort n: " + n);
        // flag记住是否发生了交换。开始的时候，默认需要交换
        boolean flag = true;
        for (int i = 1; flag && i < n - 1; i ++) {
            flag = false;
            for (int j = 0; j < n - i; j ++) {
                System.out.print("   bubbleSort j: " + j);
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);

                    flag = true;
                }
            }
        }
    }

    /**
     * 第二次优化：
     * 再做进一步优化。
     * 比如，现在有一个包含1000个数的数组。仅前面100个无序。后面900个都已排好序，且都大于前面100个
     * 数字。
     * 那么在第一趟遍历后，最后发生交换的位置必定小于100。且这个位置之后的数据必定已经有序了，
     * 也就是说这个位置以后的数据不需要再排序了，于是记录下这位置。第二次只要从数组头部遍历到这个
     * 位置就可以了。
     * 如果对于上面的冒泡排序算法来说，虽然只排序100次，但是前面的100次排序每次都要
     * 对后面的900个数据进行比较。
     * 对于算法排序3来说，只需要有一次比较后面的900个数据，之后就会设置尾边界，保证后面的900个数据
     * 不再被排序。
     *
     * @param a
     * @param n
     */
    public static void bubbleSort3(int[] a, int n) {

        int j;

        // 遍历（排序）的尾边界
        int k;

        // 一趟排序中，发生交换的结束位置，即交换的尾边界
        // flag记录最后交换的位置
        // 排序开始前，初始化flag尾边界是末尾
        // falg!=0表示发生过交换，flag==0表示未发生交换，循环排序结束
        // 是否发生过交换也是排序循环是否继续的判断依据
        int flag = n;

        while (flag > 0) {

            // 重置排序的尾边界
            k = flag;

            // 重置交换的尾边界，交换时重新记录。
            flag = 0;

            // 前面的数据大于后面的数据就交换
            for (j = 1; j < k; j ++) {
                if (a[j - 1] > a[j]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;

                    // 记录最新的尾边界
                    // 如果flag
                    flag = j;
                }
            }
        }
    }

    /**
     * 参考链接：
     * （1）https://blog.csdn.net/u010853261/article/details/54891710
     */
}
