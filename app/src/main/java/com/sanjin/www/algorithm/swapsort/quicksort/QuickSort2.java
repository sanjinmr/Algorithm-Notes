package com.sanjin.www.algorithm.swapsort.quicksort;

/**
 * 两侧遍历后，再交换 将递归和partition搞一起的
 * 以左侧为枢轴
 */
public class QuickSort2 {

    public void quickSort(int[] array,int low,int high) {

        if(low >= high) return;

        int pivotKey = low;
        int pivotValue = array[pivotKey];
        int sentryLeft = low;
        int sentryRight = high;
        int temp;

        while (sentryLeft < sentryRight) {

            //再看左边，依次往右递增
            while (sentryLeft < sentryRight && array[sentryLeft] <= pivotValue) sentryLeft ++;

            //先看右边，依次往左递减
            while (sentryLeft < sentryRight && array[sentryRight] >= pivotValue) sentryRight --;

            //如果满足条件则交换
            if (sentryLeft < sentryRight) {
                temp = array[sentryRight];
                array[sentryRight] = array[sentryLeft];
                array[sentryLeft] = temp;
            }
        }

        //最后将基准为与i和j相等位置的数字交换
        array[pivotKey] = array[sentryLeft];
        array[sentryLeft] = pivotValue;

        //递归调用左半数组
        quickSort(array, low, sentryRight - 1);
        //递归调用右半数组
        quickSort(array, sentryRight + 1, high);
    }

    public void test(){
        //int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        int[] arr = {6, 7, 9, 8, 4, 3 ,2, 6};
        //int[] arr = {8, 9, 7, 6};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
