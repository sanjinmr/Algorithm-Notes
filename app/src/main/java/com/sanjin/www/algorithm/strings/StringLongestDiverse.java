package com.sanjin.www.algorithm.strings;

import com.sanjin.www.algorithm.array.Array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * 最长快乐字符串
 *
 * leetcode 1405 中等
 * https://leetcode-cn.com/problems/longest-happy-string/
 *
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 *
 * 示例 1：
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 *
 * 示例 2：
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 *
 * 示例 3：
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 *
 *
 * 提示：
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 *
 */
public class StringLongestDiverse {

    public static void test() {
        System.out.println("result: " + longestDiverseString(1, 1, 7));
    }

    private static String longestDiverseString(int a, int b, int c) {

        Pair[] pairs = {new Pair(a, 'a'), new Pair(b, 'b'), new Pair(c, 'c')};

        StringBuilder res = new StringBuilder();

        while (true) { // 每一轮都对数组排序，并尽量取最多
            Arrays.sort(pairs, new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return o2.num - o1.num;
                }
            });

            boolean hasNext = false;

            for (Pair pair: pairs) {
                // 如果遇到一种字符检查完了，则结束本轮遍历，因为是从大到小排列的，后面也都为0了
                if (pair.num <= 0) {
                    break;
                }

                // 如果这个字符有两个连续的字符了，则跳过这个字符
                int len = res.length();
                if (len >= 2 && res.charAt(len - 1) == pair.ch && res.charAt(len - 2) == pair.ch) {
                    continue; // 注，不是break，遇到达到最多限制时，跳过这个字符，检查后面的字符
                }

                res.append(pair.ch);
                pair.num --;
                hasNext = true;
                break; // 只有取了一个字符才结束本for循环，然后重新排序
            }

            if (!hasNext) {
                break;
            }
        }

        return res.toString();
    }

    private static class Pair {
        int num;
        Character ch;

        public Pair(int num, Character ch) {
            this.num = num;
            this.ch = ch;
        }
    }
}
