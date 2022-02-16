package com.sanjin.www.algorithm.strings;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/2/16
 * desc: 字符串的字符的所有排列
 * note:
 */
public class StringPermutation {

    /**
     * 测试方法
     */
    public static void test() {
        String str = "abc";
        StringPermutation stringPermutation = new StringPermutation();
        stringPermutation.permutation(str.toCharArray(), 0);
    }

    /**
     * 计算字符串的所有排列情况 -- 递归 -- 固定第一个字符
     * note: 在理解或写这段代码的时候，脑海里呈现那副树图
     *
     * 递推公式：
     * 假设数组中存储的是1，2， 3...n。
     * f(1,2,...n) = {第一位是1, f(n-1)} + {第一位是2, f(n-1)} +...+{第一位是n, f(n-1)}。
     *
     * @param chars
     * @param begin
     */
    private void permutation(char[] chars, int begin) {

        if (chars == null || begin > chars.length - 1) {
            return;
        }

        if (begin == chars.length - 1) { // 如果还剩最后一个字符则直接打印
            print(chars);
            return;
        }

        for (int i = begin; i < chars.length; i ++) {
            swap(chars, i, begin); // 固定第begin个字符
            permutation(chars, begin + 1);
            swap(chars, i, begin); // 恢复曾被交换的第begin个字符
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private void print(char[] chars) {
        StringBuilder builder = new StringBuilder();
        for (char c : chars) {
            builder.append(c);
        }
        String str = builder.toString();
        System.out.println("result: " + str);
    }

    /**
     * 固定最后一个字符
     *
     * 递推公式：
     * 假设数组中存储的是1，2， 3...n。
     * f(1,2,...n) = {f(n-1), 最后一位是1} + {f(n-1), 最后一位是2} +...+{f(n-1), 最后一位是n}。
     *
     * @param chars
     * @param k 还剩下的未排序的字符数
     */
    private void permutation1(char[] chars, int k) {
        if (k == 1) { // 如果只剩一个未排序的字符，则直接打印
            print(chars);
            return;
        }

        for (int i = 0; i < k; i ++) {
            swap(chars, i, k - 1); // 固定最后一个字符
            permutation1(chars, k - 1);
            swap(chars, i, k - 1);
        }
    }

}
