package com.sanjin.www.algorithm.binarysearch;

public class BinarySearch {

    /**
     * 常规二分查找算法 -- 没有重复的元素 -- 循环实现
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearch(int[] a, int n, int value) {
        if (a == null || n <= 0) {
            return -1;
        }

        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;

            if (a[mid] == value) return mid;

            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 二分查找的变体一：查找第一个等于给定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchV1(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;

            if (a[mid] == value) { // 如果mid的值等于value
                if (mid == 0 || a[mid - 1] != value) { // 别忽略了mid==0且值等于value这个可能性
                    return mid;
                } else {
                    high = mid - 1;
                }
            }

            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 二分查找的变体2：查找最后一个等于查找元素的值
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchV2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
          int mid = low + (high - low) >> 1;

          if (a[mid] == value) {
              if (mid == n - 1 || a[mid + 1] != value) {
                  return mid;
              } else {
                  low = mid + 1;
              }
          }

          if (a[mid] > value) {
              high = mid - 1;
          } else if (a[mid] < value) {
              low = mid + 1;
          }
        }
        return -1;
    }

    /**
     * 二分查找算法3：查找第一个大于或等于给定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchV3(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;

            if (a[mid] >= value) {
                if (mid == 0 || a[mid - 1] < value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else if (a[mid] < value) {
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 二分查找算法4：查找最后一个小于或等于给定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchV4(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;

            if (a[mid] <= value) {
                if (mid == n - 1 || a[mid + 1] > value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else if (a[mid] > value) {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 常规的二分查找算法 -- 没有重复的元素 -- 递归实现
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchRecursion(int[] a, int n, int value) {
        if (a == null || n <= 0) {
            return -1;
        }

        return bsearchRecursion(a, 0, n - 1, value);
    }

    private int bsearchRecursion(int[] a, int low, int high, int value) {
        if (low > high) return -1;

        int mid = low + (high - low) >> 1;

        if (a[mid] == value) {
            return mid;
        }

        if (a[mid] > value) {
            return bsearchRecursion(a, low, mid - 1, value);
        } else { // a[mid] < value
            return bsearchRecursion(a, mid + 1, high, value);
        }
    }


    /**
     * 计算x的平方根，精确到6位小数
     * @param x
     * @return
     */
    public float calSQRT(float x) {
        float low = 0;
        float high = x;
        while(Math.abs(high - low) >= 0.000001) {
            float mid = (high + low)/2;
            float mid2 = mid * mid;

            if (Math.abs(mid2 - x) <= 0.000001) {
                return mid;
            }

            if (mid2 - x > 0.000001) {
                high = mid;
            } else if (x - mid2 > 0.000001) {
                low = mid;
            }
        }
        return -1;
    }

    /**
     * 如果有序数组是一个循环有序数组，比如 [4，5，6，1，2，3]。针对这种情况，如何实现一个求“值等于给定值”的二分查找算法呢？
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchInCycleSortedArray(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;
            if (a[mid] == value) return mid; // 如果mid就是目标
            // mid不是目标元素时，先判断一下哪一半是有序的
            if (a[mid] < a[low]) { // 如果mid在后半段，且后半段是有序区间
                if (a[mid] < value && value <= a[high]) { // 先在有序的后半段区间判断，如果value在后半段有序区间，则继续折半搜索
                    low = mid + 1;
                } else { // 如果后半段有序区间没有value，也继续折半搜索前半段循环有序区间
                    high = mid - 1;
                }
            } else { // 如果mid在前半段，且前半段是有序区间
                if (a[mid] > value && value >= a[low]) { // 先在有序的前半段区间判断，如果value在前半段有序区间，则继续折半搜索
                    high = mid - 1;
                } else { // 如果前半段有序区间没有value，也继续折半搜索后半段循环有序区间
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
