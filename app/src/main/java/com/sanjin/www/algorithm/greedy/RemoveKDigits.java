package com.sanjin.www.algorithm.greedy;

import java.util.Stack;

/**
 * 移除k位数字
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * note:
 * 1. 【LeetCode-402】
 */
public class RemoveKDigits {

    /**
     * 单调栈的另一个应用，思想为删除靠前的较大的数能够使得最后的数值最小。
     * 构建递增栈，若当前数字小于栈顶元素，则在满足待删减字符数不为0的情况下，栈顶元素出栈，当前元素入栈
     *
     * 用贪心算法的思想分析：
     * 从最高位开始，依次取尽量小的数字，即让最高位的值尽量最小
     *
     * 实现手段：
     * 依次将数字放到栈中，遇见比栈顶元素小的，就把栈顶元素移除，直到栈中没有比它大的元素是时再将它放到栈中，
     * 这样就能使得栈中的元素从底大顶是从小到大的顺序。即从栈底到顶排列出的数字是最小的。
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKDigits(String num, int k) {

        if (num == null) {
            return "0";
        }

        if (k >= num.length() || num.length() == 0) {
            return "0";
        }

        // 栈顶始终为最大值
        Stack<Integer> stack = new Stack<>();

        // 先存入一个数字
        stack.push(num.charAt(0) - '0');

        for (int i = 1; i < num.length(); i ++) {
            int now = num.charAt(i) - '0';

            // 保证栈中的数据尽量小
            // 可能栈中好几个值都比当前值大，那么我们就在k允许的情况下，弹出它
            while (!stack.empty() && stack.peek() > now && k > 0) {
                stack.pop();
                k --;
            }

            // 此时，栈中没有比当前now大的了。
            // 当前值不等于0时，直接将它添加到栈中
            if (now != 0) {
                stack.push(now);
            } else {
                // 如果当前值now为0，但是前面已经有其他数字了，也可以把这个0放到栈中
                // （这种情况一般是，移除的次数达到k次了，但是后面还有元素为0，需要直接把它放到栈中以便后面从栈中取出来显示）
                if (!stack.isEmpty()) {
                    stack.push(now);
                }
            }
        }

        // 如果所有数字都入栈，但是移除的次数还没有达到K次，则移除移除栈顶的元素即可。
        // 如num为56789，前面的数字一直比它后面的小，for循环后，栈中的数据还是56789，
        // 一次都没有移除过，我们就可以直接从栈顶开始移除。
        while (k > 0) {
            stack.pop();
            k --;
        }

        // 如果移除结束后，发生栈中没有剩余元素了，说明得到的数字是0
        // 举例：num为10，k为1时。
        if (stack.isEmpty()) {
            return "0";
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }

        // 从后往前添加，所以我们要逆序
        return res.reverse().toString();
    }

}
