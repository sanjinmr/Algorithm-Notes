package com.sanjin.www.algorithm.backtracking;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/4/1
 * desc: 八皇后 -- 回溯算法
 * note:
 * 1. 本算法的实现参考了王争的《数据结构与算法之美》
 */
public class EightQueens {

    // 下标表示行，值表示queen存储在哪一列
    private final int[] states = new int[8];

    public void test() {
        calEightQueensBF(0); // 最开始调用从0开始
    }

    /**
     * 回溯算法
     * 穷举搜索
     *
     * note：会打印所有符合要求的摆放结果
     *
     * @param row
     */
    private void calEightQueensBF(int row) {
        //System.out.println("calEightQueensBF row: " + row);
        if (row == 8) { // 若8个"皇后"都放好了，则打印摆放结果
            printQueens(states);
            return;
        }

        if (row > 8) { // 边界检查
            return;
        }

        for (int column = 0; column < 8; column ++) { // 每一行都有8种放法
            if (isOk(row, column)) { // 判断(row,column)位置是否符合要求
                //System.out.println("calEightQueensBF isOK row: " + row + " column: " + column);
                states[row] = column; // 第row行的"皇后"放到了column列
                calEightQueensBF(row + 1); // 考察下一行
                //return;
            }
        }
    }

    private boolean isOk(int row, int column) { // 判断row行column列放置是否合适
        //System.out.println("isOk row: " + row + " column: " + column);
        int leftUp = column - 1;
        int rigthUp = column + 1;

        for (int i = row - 1; i >= 0; i --) { // 逐行往上考察每一行
            //System.out.println("isOk1 row: " + row + " column: " + column);
            if (states[i] == column) { // 第i行的column列已经放了"皇后"了吗？
                return false;
            }
            if (leftUp >= 0 && states[i] == leftUp) { // 考察左上对角线：第i行leftup列有"皇后"吗？
                return false;
            }

            if (rigthUp < 8 && states[i] == rigthUp) { // 考察右上对角线：第i行rightup列有"皇后"吗？
                return false;
            }

            leftUp --;
            rigthUp ++;
        }

        return true;
    }

    /**
     * 打印出一个二纬矩阵
     * @param states
     */
    private void printQueens(int[] states) {
        for (int i = 0; i < 8; i ++) {
          for (int j = 0; j < 8; j ++) {
              if (states[i] == j) {
                System.out.print("Q ");
              } else {
                  System.out.print("* ");
              }
          }
          System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

}

