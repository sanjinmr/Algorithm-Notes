package com.sanjin.www.algorithm.swapsort.quicksort;

/**
 * 单侧遍历时，及时交换
 * 以左侧为枢轴
 */
public class QuickSort {

    public int partition(int[] array, int low, int high) {
        int pivot = array[low];
        while (low < high) {
            while (low < high && array[high] >= pivot) -- high;
            array[low] = array[high];
            while (low < high && array[low] <= pivot) ++ low;
            array[high] = array[low];
        }
        array[low] = pivot;
        return low;
    }

    public void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);
            quickSort(array, low, pivot - 1);
            quickSort(array, pivot + 1, high);
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
