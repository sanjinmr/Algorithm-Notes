package com.sanjin.www.algorithm.strings;

import java.util.Arrays;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/2/14
 * desc: 检查s1的几种排列情况中，是否有一种情况，是s2字符串的片段
 * note:
 */
public class StringInclusion {

    public void test() {
        String s1= "ab";
        String s2 = "eidboaooo";

        checkInclusion(s1, s2);
    }

    /**
     * 滑动窗口计数法
     * @param s1
     * @param s2
     * @return
     */
    private boolean checkInclusion(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();

        if (s1Length > s2Length) {
            return false;
        }

        int[] countS1 = new int[26];
        int[] countS2 = new int[26];

        for (int i = 0; i < s1Length; i ++) {
            ++ countS1[s1.charAt(i) - 'a'];
            ++ countS2[s2.charAt(i) - 'a'];
        }

        if (Arrays.equals(countS1, countS2)) {
            return true;
        }

        for (int i = s1Length; i < s2Length; i ++) {
            ++ countS2[s2.charAt(i) - 'a'];
            -- countS2[s2.charAt(i - s1Length) - 'a'];
            if (Arrays.equals(countS1, countS2)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 滑动窗口差值法
     * @param s1
     * @param s2
     * @return
     */
    private boolean checkInclusion1(String s1, String s2) {

        if (s1 == null || s2 == null) {
            return false;
        }

        int s1Length = s1.length();
        int s2Length = s2.length();

        int diff = 0;

        int[] count = new int[26];

        for (int i = 0; i < s1Length; i ++) {
            -- count[s1.charAt(i) - 'a'];
            ++ count[s2.charAt(i) - 'a'];
        }

        for (int i : count) {
            if (i != 0) {
                ++ diff;
            }
        }

        if (diff == 0) {
            return true;
        }

        for (int i = s1Length; i < s2Length; i ++) {
            int x = s2.charAt(i) - 'a';
            int y = s2.charAt(i - s1Length) - 'a';
            if (x == y) {
                continue;
            }

            if (count[x] == 0) {
                ++ diff;
            }
            ++ count[x];
            if (count[x] == 0) {
                -- diff;
            }

            if (count[y] == 0) {
                ++ diff;
            }
            -- count[y];
            if (count[y] == 0) {
                -- diff;
            }

            if (diff == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 双指针法
     * @param s1
     * @param s2
     * @return
     */
    private boolean checkInclusion2(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }

        int s1Length = s1.length();
        int s2Length = s2.length();

        if (s1Length > s2Length) {
            return false;
        }

        int[] count = new int[26];
        for (int i = 0; i < s1Length; i ++) {
            -- count[s1.charAt(i)- 'a'];
        }

        int left = 0;
        for (int right = 0; right < s2Length; right ++) {
            int x = s2.charAt(right) - 'a';
            ++ count[x];

            while (count[x] > 0) {
                -- count[s2.charAt(left) -'a'];
                ++ left;
            }

            if (right - left + 1 == s1Length) {
                return true;
            }
        }

        return false;
    }
}
