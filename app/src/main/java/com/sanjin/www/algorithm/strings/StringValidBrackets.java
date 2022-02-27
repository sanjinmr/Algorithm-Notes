package com.sanjin.www.algorithm.strings;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * LeetCode 20 简单
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * 描述：
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * note：
 * 1、在腾讯的第四面中遇到过这个问题
 *
 */
public class StringValidBrackets {

    Map<Character, Character> pairs = new HashMap<>();

    public static void test() {
        System.out.println("result: " + new StringValidBrackets().isValid("{[()]}[][[][]"));
    }

    private boolean isValid(String str) {

        pairs.put('}', '{');
        pairs.put(']', '[');
        pairs.put(')', '(');

        if (str == null || str.length() == 0 || str.replace(" ", "").length() == 0) {
            return false;
        }

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < str.length(); i ++) {
            Character ch = str.charAt(i);
            if (pairs.containsKey(ch)) { // 如果这个字符是右括号
                if (stack.isEmpty() || !stack.peek().equals(pairs.get(ch))) { // 栈顶找不到匹配的字符
                    return false;
                }
                stack.pop(); // 栈顶找到了匹配的字符则说明它是合法的，可以将去弹出
            } else { // 如果这个字符是左括号
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }

}
