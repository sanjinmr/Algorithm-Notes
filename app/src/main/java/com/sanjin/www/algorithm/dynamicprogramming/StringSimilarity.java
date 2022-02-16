package com.sanjin.www.algorithm.dynamicprogramming;

/**
 * 字符串相似度问题
 * 1、
 * 莱文斯坦距离的大小，表示两个字符串差异的大小。莱文斯坦距离允许增加、删除、替换字符这三个编辑操作。
 * 用回溯算法的思路分析莱文斯坦距离的递归过程：
 * 如果 a[i]与 b[j]匹配，我们递归考察 a[i+1]和 b[j+1]。如果 a[i]与 b[j]不匹配，那我们有多种处理方式可选：
 * 可以删除 a[i]，然后递归考察 a[i+1]和 b[j]；
 * 可以删除 b[j]，然后递归考察 a[i]和 b[j+1]；
 * 可以在 a[i]前面添加一个跟 b[j]相同的字符，然后递归考察 a[i]和 b[j+1];
 * 可以在 b[j]前面添加一个跟 a[i]相同的字符，然后递归考察 a[i+1]和 b[j]；
 * 可以将 a[i]替换成 b[j]，或者将 b[j]替换成 a[i]，然后递归考察 a[i+1]和 b[j+1]。
 *
 * 状态转移方程：
 * 如果：a[i]!=b[j]，那么：min_edist(i, j)就等于：
 * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1, min_edist(i-1,j-1)+1)
 * 如果：a[i]==b[j]，那么：min_edist(i, j)就等于：
 * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1，min_edist(i-1,j-1))
 * 其中，min表示求三数中的最小值。
 *
 * 2、
 * 最长公共子串的大小，表示两个字符串相似程度的大小。最长公共子串长度只允许增加、删除字符这两个编辑操作。
 *
 * 我们先来看回溯的处理思路。我们从 a[0]和 b[0]开始，依次考察两个字符串中的字符是否匹配。
 * 如果 a[i]与 b[j]互相匹配，我们将最大公共子串长度加一，并且继续考察 a[i+1]和 b[j+1]。
 * 如果 a[i]与 b[j]不匹配，最长公共子串长度不变，这个时候，有两个不同的决策路线;
 * 删除 a[i]，或者在 b[j]前面加上一个字符 a[i]，然后继续考察 a[i+1]和 b[j]；
 * 删除 b[j]，或者在 a[i]前面加上一个字符 b[j]，然后继续考察 a[i]和 b[j+1]。
 *
 * 反过来也就是说，如果我们要求 a[0...i]和 b[0...j]的最长公共长度 max_lcs(i, j)，我们只有可能通过下面三个状态转移过来：
 *
 * (i-1, j-1, max_lcs)，其中 max_lcs 表示 a[0...i-1]和 b[0...j-1]的最长公共子串长度；
 * (i-1, j, max_lcs)，其中 max_lcs 表示 a[0...i-1]和 b[0...j]的最长公共子串长度；
 * (i, j-1, max_lcs)，其中 max_lcs 表示 a[0...i]和 b[0...j-1]的最长公共子串长度。
 *
 * 如果我们把这个转移过程，用状态转移方程写出来，就是下面这个样子：
 *
 * 如果：a[i]==b[j]，那么：max_lcs(i, j)就等于：
 * max(max_lcs(i-1,j-1)+1, max_lcs(i-1, j), max_lcs(i, j-1))；
 * 如果：a[i]!=b[j]，那么：max_lcs(i, j)就等于：
 * max(max_lcs(i-1,j-1), max_lcs(i-1, j), max_lcs(i, j-1))；
 * 其中max表示求三数中的最大值。
 *
 */
public class StringSimilarity {

    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
    private int n = 6;
    private int m = 6;
    private int minDist = Integer.MAX_VALUE; // 存储结果

    /**
     * 计算莱文斯坦距离 -- 回溯算法
     *
     * 调用方式 lwstBT(0, 0, 0);
     *
     * @param i
     * @param j
     * @param edist
     */
    public void lwstBT(int i, int j, int edist) {
        if (i == n || j == m) {
            if (i < n) edist += (n-i);
            if (j < m) edist += (m - j);
            if (edist < minDist) minDist = edist;
            return;
        }
        if (a[i] == b[j]) { // 两个字符匹配
            lwstBT(i+1, j+1, edist);
        } else { // 两个字符不匹配
            lwstBT(i + 1, j, edist + 1); // 删除a[i]或者b[j]前添加一个字符
            lwstBT(i, j + 1, edist + 1); // 删除b[j]或者a[i]前添加一个字符
            lwstBT(i + 1, j + 1, edist + 1); // 将a[i]和b[j]替换为相同字符
        }
    }

    /**
     * 计算莱文斯坦距离 -- 状态转移表法
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int lwstDP(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];

        for (int j = 0; j < m; ++j) { // 初始化第0行:a[0..0]与b[0..j]的编辑距离
            if (a[0] == b[j]) minDist[0][j] = j;
            else if (j != 0) minDist[0][j] = minDist[0][j-1]+1;
            else minDist[0][j] = 1;
        }
        for (int i = 0; i < n; ++i) { // 初始化第0列:a[0..i]与b[0..0]的编辑距离
            if (a[i] == b[0]) minDist[i][0] = i;
            else if (i != 0) minDist[i][0] = minDist[i-1][0]+1;
            else minDist[i][0] = 1;
        }

        for (int i = 1; i < n; ++i) { // 按行填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) minDist[i][j] = min(
                        minDist[i-1][j]+1, minDist[i][j-1]+1, minDist[i-1][j-1]);
                else minDist[i][j] = min(
                        minDist[i-1][j]+1, minDist[i][j-1]+1, minDist[i-1][j-1]+1);
            }
        }

        return minDist[n-1][m-1];
    }

    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv) minv = x;
        if (y < minv) minv = y;
        if (z < minv) minv = z;
        return minv;
    }

    /**
     * 计算最长公共子串长度 -- 状态转移表法实现
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int lcs(char[] a, int n, char[] b, int m) {
        int[][] maxlcs = new int[n][m];
        for (int j = 0; j < m; ++j) {//初始化第0行：a[0..0]与b[0..j]的maxlcs
            if (a[0] == b[j]) maxlcs[0][j] = 1;
            else if (j != 0) maxlcs[0][j] = maxlcs[0][j-1];
            else maxlcs[0][j] = 0;
        }
        for (int i = 0; i < n; ++i) {//初始化第0列：a[0..i]与b[0..0]的maxlcs
            if (a[i] == b[0]) maxlcs[i][0] = 1;
            else if (i != 0) maxlcs[i][0] = maxlcs[i-1][0];
            else maxlcs[i][0] = 0;
        }

        for (int i = 1; i < n; ++i) { // 填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) maxlcs[i][j] = max(
                        maxlcs[i-1][j], maxlcs[i][j-1], maxlcs[i-1][j-1]+1);
                else maxlcs[i][j] = max(
                        maxlcs[i-1][j], maxlcs[i][j-1], maxlcs[i-1][j-1]);
            }
        }

        return maxlcs[n-1][m-1];
    }

    private int max(int x, int y, int z) {
        int maxv = Integer.MIN_VALUE;
        if (x > maxv) maxv = x;
        if (y > maxv) maxv = y;
        if (z > maxv) maxv = z;
        return maxv;
    }


}
