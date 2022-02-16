package com.sanjin.www.algorithm.swapsort.quicksort;

/**
 * 单侧遍历时，及时交换 三数取中
 * 以左侧为枢轴
 */
public class QuickSort1 {

    public static void swap(int a, int b){
        int temp = a;
        a = b;
        b = temp;
    }

    /**
     * 对于基准位置的选取一般有三种方法：固定切分，随机切分和三取样切分。
     * 固定切分的效率并不是太好，随机切分是常用的一种切分，效率比较高，
     * 最坏情况下时间复杂度有可能为O(N2).对于三数取中选择基准点是最理想的一种。
     * @param array
     * @param low
     * @param high
     * @return
     */
    public int partition(int[] array, int low, int high) {
        //三数取中
        int mid = low + (high - low) / 2;
        if(array[mid] > array[high]){
            swap(array[mid], array[high]);
        }
        if(array[low] > array[high]){
            swap(array[low], array[high]);
        }
        if(array[mid] > array[low]){
            swap(array[mid], array[low]);
        }

        int temp = array[low];
        while (low < high) {
            while (low < high && array[high] >= temp) -- high;
            array[low] = array[high];
            while (low < high && array[low] <= temp) ++ low;
            array[high] = array[low];
        }
        array[low] = temp;
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
