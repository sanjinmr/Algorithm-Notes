package com.sanjin.www.algorithm.selectsort.heapsort;

import java.util.Arrays;

@Deprecated
public class MaxHeapSort2 {

    // 求parent/left child/right child:

    private int parent(int i) {return (i - 1) / 2;}
    private int left(int i) {return 2 * i + 1;}
    private int right(int i) {return 2 * i + 2;}

    // 保持最大堆特性：

    private void maxHeapAdjust(int[] array, int heapsize, int i) {
        int left = left(i);
        int right = right(i);
        int largest = i;
        if (left <= heapsize - 1 && array[left] > array[i]) {
            largest = left;
        }
        if (right <= heapsize - 1 && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = array[i];
            // swap
            array[i] = array[largest];
            array[largest] = temp;
            maxHeapAdjust(array, heapsize, largest);
        }
    }

    // 构造一个“最大堆”：

    private void buildMaxHeap(int[] array, int heapsize) {
        for (int i = parent(heapsize - 1); i >= 0; i --) {
            maxHeapAdjust(array, heapsize, i);
        }
    }

    // 对一个array使用heapsort:

    public void heapsort(int[] array){
        int heapsize = array.length;

        buildMaxHeap(array, heapsize);

        int step = 1;
        for (int i = array.length - 1; i > 0; i --) {
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            heapsize --;
            System.out.println("Step: " + (step++) + Arrays.toString(array));
            maxHeapAdjust(array, heapsize, 0);
        }
    }

    // main函数:

    public void test() {
        //a sample input
        int[] array = {3, 7, 2, 11, 3, 4, 9, 2, 18, 0};
        System.out.println("Input: " + Arrays.toString(array));
        heapsort(array);
        System.out.println("Output: " + Arrays.toString(array));
    }

    /*
    运行结果:

    Input: [3.0, 7.0, 2.0, 11.0, 3.0, 4.0, 9.0, 2.0, 18.0, 0.0]
    Step: 1[0.0, 11.0, 9.0, 7.0, 3.0, 4.0, 2.0, 2.0, 3.0, 18.0]
    Step: 2[0.0, 7.0, 9.0, 3.0, 3.0, 4.0, 2.0, 2.0, 11.0, 18.0]
    Step: 3[2.0, 7.0, 4.0, 3.0, 3.0, 0.0, 2.0, 9.0, 11.0, 18.0]
    Step: 4[2.0, 3.0, 4.0, 2.0, 3.0, 0.0, 7.0, 9.0, 11.0, 18.0]
    Step: 5[0.0, 3.0, 2.0, 2.0, 3.0, 4.0, 7.0, 9.0, 11.0, 18.0]
    Step: 6[0.0, 3.0, 2.0, 2.0, 3.0, 4.0, 7.0, 9.0, 11.0, 18.0]
    Step: 7[0.0, 2.0, 2.0, 3.0, 3.0, 4.0, 7.0, 9.0, 11.0, 18.0]
    Step: 8[2.0, 0.0, 2.0, 3.0, 3.0, 4.0, 7.0, 9.0, 11.0, 18.0]
    Step: 9[0.0, 2.0, 2.0, 3.0, 3.0, 4.0, 7.0, 9.0, 11.0, 18.0]
    Step: 10[0.0, 2.0, 2.0, 3.0, 3.0, 4.0, 7.0, 9.0, 11.0, 18.0]
    Output: [0.0, 2.0, 2.0, 3.0, 3.0, 4.0, 7.0, 9.0, 11.0, 18.0]
    */

}
