package com.sanjin.www.algorithm.swapsort.quicksort;

/**
 * 两侧遍历后，再交换
 * 以右边为枢轴
 */
public class QuickSort5 {

    public int partition(int[] array, int low, int high) {
        // 记录枢轴的值
        int pivot = array[high];
        while (low < high) {
            //再看左边，依次往左递增
            while (low < high && array[low] <= pivot) ++ low;
            array[high] = array[low];
            //再看右边，依次往右递减
            while (low < high && array[high] >= pivot) -- high;
            array[low] = array[high];
        }
        //最后将枢轴的值赋给中间位置
        array[high] = pivot;
        return high;
    }

    public void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotLoc = partition(array, low, high);
            //递归调用左半数组
            quickSort(array, low, pivotLoc - 1);
            //递归调用右半数组
            quickSort(array, pivotLoc + 1, high);
        }
    }

    public void test(){
        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
