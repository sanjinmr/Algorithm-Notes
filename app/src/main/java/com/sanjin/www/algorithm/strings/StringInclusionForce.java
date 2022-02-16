package com.sanjin.www.algorithm.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/2/14
 * desc: 检查s1的几种排列情况中，是否有一种情况，是s2字符串的片段
 * note: 暴力解法，先计算出s2的排列情况，然后判断是否是s1的片段
 */
public class StringInclusionForce {

    private String s2;
    private List<String> list = new ArrayList();
    private boolean hasFound = false;

    public void test() {
        System.out.println("result: " + checkInclusion("ab", "eidbaooo"));
        for (String str : list) {
            System.out.println("result str: " + str);
        }
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }

        this.s2 = s2;

        permutation(s2.toCharArray(), 0);

        return hasFound;
    }

    public void permutation(char[] chars, int begin) {
        if(chars == null || begin == -1 || begin > chars.length - 1) {
            return;
        }
        if (begin == chars.length - 1) {
            StringBuilder builder = new StringBuilder();
            for (char c : chars) {
                builder.append(c);
            }
            String str = builder.toString();
            if (s2.contains(str)) {
                hasFound = true;
            }
            return;
        }

        for (int i = begin; i < chars.length; i ++) {
            if (hasFound) {
                return;
            }
            swap(chars, i, begin);
            permutation(chars, begin + 1);
            swap(chars, i, begin);
        }
    }

    private void swap(char[] chars, int i, int begin) {
        char temp = chars[i];
        chars[i] = chars[begin];
        chars[begin] = temp;
    }

}
