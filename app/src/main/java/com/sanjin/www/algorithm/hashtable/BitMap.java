package com.sanjin.www.algorithm.hashtable;

/**
 * 位图
 *
 * note：
 * 1、这里用char来表示二进制位
 * 2、位图通过数组下标来定位数据，所以，访问效率非常高。
 * 而且，每个数字用一个二进制位来表示，在数字范围不大的情况下，所需要的内存空间非常节省。
 * 3、适合场景：数字所在的范围不是很大。如果数字范围很大，就不适合了，空间复杂度还不如散列表。
 *
 */
public class BitMap {

    private char[] chars;
    private int nbits;

    public BitMap(int nbits) {
        this.nbits = nbits;
        // 当nbits小于16时，也有char存储，所以这个需要+1
        this.chars = new char[nbits/16 + 1];
    }

    public void set(int k) {
        if (k > nbits) return; // 边界检查
        int charIndex = k / 16;
        int bitIndex = k % 16;
        chars[charIndex] |= (1 << bitIndex);
    }

    public boolean get(int k) {
        if (k > nbits) return false;
        int charIndex = k / 16;
        int bitIndex = k % 16;
        return (chars[charIndex] & (1 << bitIndex)) != 0;
    }
}
