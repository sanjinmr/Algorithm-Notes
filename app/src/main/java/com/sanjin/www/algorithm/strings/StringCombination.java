package com.sanjin.www.algorithm.strings;

import com.sanjin.www.algorithm.array.Array;

import java.util.ArrayList;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/2/16
 * desc: 字符串的字符的所有组合
 * note:
 */
public class StringCombination {

    public static void test() {
        String str = "abc";
        char[] chars = str.toCharArray();
        StringCombination combination = new StringCombination();
        combination.combination(chars);
    }

    private void combination(char[] chars) {
        ArrayList<Character> results = new ArrayList<>();
        for (int i = 0; i < chars.length; i ++) {
            // 从数组中的第一个字符开始依次设组合的长度为1~str.length，
            // 每次都从索引为0的位置开始取
            combinationInter(chars, i, 0, results);
        }
    }

    /**
     *
     * @param chars 待排列组合的字符数组
     * @param begin 待取的下一个字符在数组的起始位置索引
     * @param num 表示还需要取的字符的个数
     * @param results 保存每一种组合的字符
     */
    private void combinationInter(char[] chars, int num, int begin, ArrayList<Character> results) {
        // 如果待排列的字符数组为空，或长度为0，则结束计算组合的行为（没有可用的字符来创建组合）。
        if (chars == null || chars.length == 0) {
            return;
        }

        // num==0表示，还需要0个字符就将本轮组合的字符取完了，可以打印该组合了
        if (num == 0) {
            System.out.println("result: " + results);
            return;
        }

        // 如果下一个待取的字符的索引超出“待排列数组”，则结束本轮递归
        if (begin > chars.length - 1) {
            return;
        }

        // 处理选中和不选中，begin位置的字符，的情况
        // 1.本次组合中，添加begin所在的字符
        results.add(chars[begin]);
        // 在剩下的字符中选择其余的num-1个字符进入组合
        combinationInter(chars, num - 1, begin + 1, results);
        // 2. 本次组合中，不添加begin所在的字符
        results.remove(chars[begin]);
        // 在剩下的字符中选择其余的num个字符进入组合
        combinationInter(chars, num, begin + 1, results);
    }

}
