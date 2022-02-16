package com.sanjin.www.algorithm.selectsort.simpleselect;

/**
 * author: sanji
 * email: sanjinmr/5/7
 * desc:
 * note:
 
 选择排序的基本思想：
 每一趟在n-i+1(i=1,2,...n-1)个记录(选择区间)中选取关键字最小的记录；并作为有序序列中第i个记录；
 
 其中最简单的是简单选择排序：
 通过n-i次关键字间的比较，从n-i+1个记录(选择区间)中，选择出关键字最小的记录，并和第i(1<=i<=n)个记录交换之。
 
 */
 public class SimpleSelectSort {

	/**
	 * 选择区间从0~n-1开始。比从1~n-1开始，多遍历一次索引为0的情况。
	 * 推荐使用：selectSort
	 * @param a
	 * @param n
	 */
	public void selectSort0(int[] a, int n) {
		if (n > a.length) {
			System.out.println("超出数组长度");
			System.exit(1);
		}

		for (int i = 0; i < n; i ++) {
			int minIndex = i;
			for (int j = i; j < n; j ++) {
				if (a[j] < a[minIndex]) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				int temp = a[i];
				a[i] = a[minIndex];
				a[minIndex] = temp;
			}
		}
	}

	/**
	 * 默认首位有序，选择区间从1~n-1开始
	 * @param a
	 * @param n
	 */
	 public void selectSort(int[] a, int n) {
		 if (n > a.length) {
			System.out.println("超出数组长度");
			System.exit(1);
		 }
		 
		 // 选择区间是从1~n-1开始的。
		 // 从排序区间选择一个认为是最小的元素放在排序区间前面
		 // i表示次数，共进行n-1次选择和交换
		 for (int i = 1; i < n; i ++) {
			// minIndex表示最小元素的下标。
			// 默认排序区间前面第一个元素为最小。
			int minIndex = i -1;
			// 找到当前排序区间最小元素的下标
			for (int j = i; j < n; j ++) {
				if (a[j] < a[minIndex]) {
					// 如果后面的元素小于当前最小元素，则记录下最小元素的下标
					minIndex = j;
				}
			}
			// 如果minIndex是选择区间前面第一个元素，
			// 说明选择区间没有更小的元素，不用交换，循环继续，选择区间减少
			if (minIndex != i -1) {
				int temp = a[i - 1];
				a[i -1] = a[minIndex];
				a[minIndex] = temp;
			}
		 }
	 }
	 
 }
 
 
 /**
	效率：
	在直接选择排序中，共需要进行n-1次选择和交换；
	每次选择需要比较n-i次（i<=i<=n-1）；
	每次交换最多3次移动；
	无论初始排列如何，所需进行的关键字间的比较次数相同，均为n(n-1)/2；
	因此总的时间复杂度是O(n^2)
	
	由于在直接选择排序中，存在前后元素之间的互换，因而可能会改变相同元素的前后位置。所以此方法是不稳定的。
 **/
 
