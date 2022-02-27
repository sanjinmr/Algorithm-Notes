package com.sanjin.www.algorithm.strings;


/**
 * 将字符串转换为整形的数字
 *
 * note:
 * 1、java integer的取值范围是从【-2147483648 至 2147483647】,包括【-2147483648】和【2147483647】，
 * 取值【-128--127】的时候效率最高（享元模式复用了）。
 *
 */
public class StringToInt {

    public static void test() {
        int result = new StringToInt().parseInt("-2147483648");
        System.out.println("result: " + result);
    }

    /**
     * 注：Integer的最小值的绝对值比最大值大。如果要想先用正数result来表示整形，是可能有问题的。因为2147483648没法用正数表示。
     * 所以有几种思路：
     * 1、先用long来表示result。然后在每次计算后判断边界。
     * 2、依然用Integer表示result，但result默认先是正数，且在判断边界的时候，要做一下巧妙的处理，
     * 不能直接用result*10或result+digit，而是先用limit/10和limit-digit来判断。同时，还要区分是正数还是负数，如果是负数的时候limit还要+1。
     * 3、依然用Integer来表示result，但result默认先是负数，
     * 因为Integer的最小值的绝对值比最大值大，所以在判断边界的时候不用判断正数和负数
     * 说明：在Integer.parseInt方法中就是用了这种思路实现的字符串和Integer的转换
     *
     * 下面我以用Integer表示result，且默认先计算正数
     *
     * @param str
     * @return
     */
    private int parseInt(String str) {
        int result = 0;

        if (str == null || str.length() == 0 || str.replace(" ", "").length() == 0) {
            return 0;
        }

        int length = str.length();
        int index = 0;
        boolean negative = false;
        if (str.charAt(index) == '+') {
            if (length == 1) {
                return 0;
            }
            negative = false;
            index ++;
        }
        if (str.charAt(index) == '-') {
            if (length == 1) {
                return 0;
            }
            negative = true;
            index ++;
        }

        int limit = Integer.MAX_VALUE;
        int mulLimit = limit / 10; // 正数乘法限制

        while (index < length) {
            if (str.charAt(index) >= '0' || str.charAt(index) <= '9') {
                int digit = str.charAt(index) - '0';

                if (result > mulLimit) { // 判断剩10后不越界
                    return 0;
                }
                result *= 10; // 先剩10

                if (!negative) { // 如果是正数
                    if (result > limit - digit) { // 判断加digit后不越界
                        return 0;
                    }
                    result += digit;
                } else { // 如果是负数
                    if (result > limit - digit + 1) { // 负数绝对值的limit要大一个数字
                        return 0;
                    }

                    result += digit;
                }

                index ++;
            } else {
                return 0;
            }
        }

        if (negative) {
            if (result + Integer.MIN_VALUE > 0) { // 排除-result< Integer.MIN_VALUE
                return 0;
            } else {
                return -1 * result;
            }
        } else {
            return result;
        }
    }

}
