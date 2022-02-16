package com.sanjin.swordfingeralgorithm2.algorithm;

import java.util.ArrayList;
import java.util.Collections;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/1/24
 * desc:
 * note:
 */
public class StringPermutation {

    // 132ms 1186K
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if(str == null || str.length() == 0)
            return list;
        char[] strArr = str.toCharArray();
        permu(list, strArr, 0);
        Collections.sort(list);
        return list;

    }

    private void permu(ArrayList<String> list, char[] strArr, int start) {

        // �ݹ���ֹ����
        if(start == strArr.length-1){
            String result = String.valueOf(strArr);
            // indexOf() ���ش��б����״γ��ֵ�ָ��Ԫ�ص���������������б�����Ԫ�أ��򷵻� -1��
            // ��Ϊ�������ַ��ظ�������Ҫ�����ж�
            if(list.indexOf(result) < 0){
                list.add(result);
            }
        }
        for(int i = start; i < strArr.length; i++) {

            char temp = strArr[start];
            strArr[start] = strArr[i];
            strArr[i] = temp;

            permu(list, strArr, start+1);

            temp = strArr[start];
            strArr[start] = strArr[i];
            strArr[i] = temp;
        }
    }

}
