package com.sanjin.www.algorithm.swapsort.quicksort;

/**
 * 两侧遍历后，再交换
 * 以左侧为枢轴
 */
public class QuickSort3 {

    public int partition(int[] array, int low, int high) {

        int temp;

        // 记录枢轴的值
        int pivotLoc = low;

        while (low < high) {
            //先看右边，依次往左递减
            while (low < high && array[high] >= array[pivotLoc]) -- high;
            //再看左边，依次往右递增
            while (low < high && array[low] <= array[pivotLoc]) ++ low;

            //如果满足条件则交换
            if (low < high) {
                temp = array[high];
                array[high] = array[low];
                array[low] = temp;
            }
        }

        System.out.println();
        System.out.println("partition pivot: " + array[pivotLoc]);
        System.out.println("partition low: " + low + "-" + array[low]);
        System.out.println("partition high: " + high + "-" + array[high]);

        //最后将枢轴的值和中间位置的值交换
        temp = array[pivotLoc];
        array[pivotLoc] = array[low];
        array[low] = temp;
        return low;
    }

    public void quickSort(int[] array, int low, int high) {
        if (low < high) {
            print(array, low, high);
            int pivotLoc = partition(array, low, high);
            //递归调用左半数组
            quickSort(array, low, pivotLoc - 1);
            //递归调用右半数组
            quickSort(array, pivotLoc + 1, high);
        }
    }

    public void test(){
        //int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        //int[] arr = {6,1,2,7,9,12,4,5,10,8};
        int[] arr = {6,1,2,7,9,3,4,5,10,8};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public void print(int[] array, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(array[i] + " ");
        }
    }

    /**
     * 记录一组日志：
     * 6 1 2 7 9 3 4 5 10 8
     * partition pivot: 6
     * partition low: 5-3
     * partition high: 5-3
     * 3 1 2 5 4
     * partition pivot: 3
     * partition low: 2-2
     * partition high: 2-2
     * 2 1
     * partition pivot: 2
     * partition low: 1-1
     * partition high: 1-1
     * 5 4
     * partition pivot: 5
     * partition low: 4-4
     * partition high: 4-4
     * 9 7 10 8
     * partition pivot: 9
     * partition low: 8-8
     * partition high: 8-8
     * 8 7
     * partition pivot: 8
     * partition low: 7-7
     * partition high: 7-7
     * 1 2 3 4 5 6 7 8 9 10
     *
     * Process finished with exit code 0
     */
}
