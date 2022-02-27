package com.sanjin.www.algorithm;

import com.sanjin.www.algorithm.dynamicprogramming.MinCoin;
import com.sanjin.www.algorithm.mergesort.MergeSort;
import com.sanjin.www.algorithm.radixsort.RadixSortMSD;
import com.sanjin.www.algorithm.insertsort.shellsort.AlgorithmShell1;
import com.sanjin.www.algorithm.strings.StringToInt;
import com.sanjin.www.algorithm.swapsort.quicksort.QuickSort2;
import com.sanjin.www.algorithm.trees.BST;
import com.sanjin.www.algorithm.trees.BinaryTreeTraversal;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void shellAlgorithmTest() {
        int[] a = {80, 30, 60, 40, 20, 10, 50, 70};

        //AlgorithmShell.shellSort2(a, a.length);
        AlgorithmShell1.shellSort(a, a.length);

        System.out.println("");
        System.out.println("排序后：");
        for (int i = 0; i < a.length; i ++) {
            System.out.print(a[i] + "; ");
        }
        System.out.println("");
    }

    @Test
    public void quickSort() {
        new QuickSort2().test();
    }

    @Test
    public void sortTest() {
        int[] a = {9, 8, 7, 80, 30, 60, 11, 2, 40, 20, 11, 11, 6, 10, 50, 70, 3, 1, 0, 4, 5, 11};

        //AlgorithmShell.shellSort2(a, a.length);
        //InsertDirectSort.insertSort2(a, a.length);

        //new SimpleSelectSort().selectSort0(a, a.length);

        //new MaxHeapSort1().heapSort(a);

        //BubblingSort.bubbleSort3(a, a.length);

        //new MaxHeapSort().heapSort(a, a.length);

        //print(CountSort.countSort(a, a.length, 80));

        int n = a.length;
        simpleSelectSort1(a, n);

        System.out.println(Arrays.toString(a));
    }

    private void simpleSelectSort1(int[] a, int n) {
        // 循环：i表示待排序元素； 正序；
        // 区间：(0, n]; step: i ++
        for (int i = 0; i < n; i ++) {
            int minIndex = i;

            // 寻找
            // 循环： j表示本轮选择时剩余的待排序元素；正序
            // 区间： (i, n]；step: j ++
            for (int j = i; j < n; j ++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            // 交换
            if (minIndex != i) {
                int temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
    }

    private void print(int[] A) {
        System.out.println("");
        System.out.println("排序后：");
        for (int i = 0; i < A.length; i ++) {
            System.out.print(A[i] + "; ");
        }
        System.out.println("");
    }

    @Test
    public void testLsd() {
        //RadixSortLSD.test2();
        RadixSortMSD.test2();
    }

    @Test
    public void testMergeSort() {
        int[] a = {9, 8, 7, 80, 30, 60, 11, 2, 40, 20, 11, 11, 6, 10, 50, 70, 3, 1, 0, 4, 5, 11};
        int[] temp = new int[a.length];

        MergeSort.mergeSort(a, 0, a.length - 1, temp);

        print(a);
    }

    @Test
    public void testRecall() {
        //RecallTest.test();
    }

    @Test
    public void testBST() {
        //BST.test();
    }

    @Test
    public void test11() {
        //StockQues.test();
        //Knapsack01.test();
        //new EightQueens().test();
        //new MiniCoin().test3();
        //new DigitalSeqMaxIncrementLength().test3();

        //new PacksQue01Plus().testStateFun();

        new MinCoin().test2();
    }

    @Test
    public void test12() {
        //BinaryTreeTraversal.test();
        Double d = Math.pow(10,  3);
        System.out.println("result:" + d);
    }

    @Test
    public void test13() {
        StringToInt.test();
    }
}