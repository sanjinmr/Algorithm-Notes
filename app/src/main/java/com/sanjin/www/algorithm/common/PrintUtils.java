package com.sanjin.www.algorithm.common;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2020/3/11
 * desc:
 * note:
 */
public class PrintUtils {
    public static void print(String msg) {
        System.out.print(msg);
    }

    public static void println(String msg) {
        System.out.println(msg);
    }

    public static String array2String(int[] array) {
        String msg = "";

        for (int value : array) {
            msg = msg + " 、 " + value;
        }

        msg = msg.replaceFirst("、", "");

        return msg;
    }
}
