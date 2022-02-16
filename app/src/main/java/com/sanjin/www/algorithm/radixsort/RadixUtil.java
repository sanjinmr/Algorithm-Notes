package com.sanjin.www.algorithm.radixsort;

public class RadixUtil {

    /**
     * 计算机高位排序的最大位数（包含）
     * @param arrayRaw
     * @param length
     * @return
     */
    public static int maxDigitMsdIn(int[] arrayRaw, int length) {
        int max = arrayRaw[0];
        for(int i = 1 ;i < length;i++){
            if(max < arrayRaw[i]) max = arrayRaw[i];
        }
        // 获取数组中最长元素长度
        int maxL = String.valueOf(max).length();
        Double d = Math.pow(10, maxL - 1);
        return d.intValue();
    }

    /**
     * 计算机低位排序的最大位数（不包含）
     * @param arrayRaw
     * @param length
     * @return
     */
    public static int maxDigitMsdEx(int[] arrayRaw, int length) {
        // 计算出最大位数
        int max = arrayRaw[0];
        for(int i = 1 ;i < length;i++){
            if(max < arrayRaw[i])  max = arrayRaw[i];
        }
        // 获取数组中最长元素长度
        int maxL = String.valueOf(max).length();
        Double d = Math.pow(10,  maxL);
        return d.intValue();
    }
}
